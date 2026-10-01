# LOJ

LOJ is a helper library for LibreOffice programming with Java.

LibreOffice provides the powerful [UNO API](https://api.libreoffice.org/), but using UNO directly from Java often requires many interfaces, `UnoRuntime.queryInterface()` calls, property operations, and connection-management code.

LOJ provides utilities and generated proxy classes that make LibreOffice objects easier to use from ordinary Java code.

In particular, it provides higher-level abstractions for Calc such as documents, sheets, ranges, and cells.

## Overview

LOJ consists of the following modules:

### loj-util

Utilities for connecting Java applications to LibreOffice.

It includes:

- LibreOffice process management
- Socket connections
- Pipe connections
- Connection pooling support
- Java / UNO stream conversion
- Temporary LibreOffice user directory support

For example, LOJ can start or connect to a LibreOffice process and obtain an UNO component context.

### loj-proxy

A source-code generator for creating Java wrapper classes from UNO interfaces.

UNO programming often requires code such as:

```java
XSomething xSomething =
    UnoRuntime.queryInterface(XSomething.class, object);

xSomething.someMethod();
```

`loj-proxy` generates wrapper classes that hide much of this interface conversion.

For example, proxy classes such as:

```text
P_Doc
P_Sheet
P_Range
P_Cell
P_Chart
```

can be generated from combinations of LibreOffice UNO interfaces.

The interfaces used to generate each proxy are defined in `proxy.properties`.

The generated methods internally perform the required UNO interface conversions.

## Proxy generation

`ProxyCreator` reads the interface definitions from `proxy.properties`.

For example, conceptually:

```properties
loproxy.table.P_Sheet=com.sun.star.sheet.XSpreadsheet ...
```

LOJ examines the specified interfaces using Java Reflection and generates a Java class implementing their methods.

The generated class delegates operations to the underlying UNO object.

The basic structure is:

```text
UNO interfaces
      |
      v
  loj-proxy
      |
      v
generated P_* classes
      |
      v
high-level Java classes
```

Generated proxy classes can then be extended with application-friendly methods.

For example:

```java
public class Sheet extends P_Sheet {
    public Sheet(Object object) {
        super(object);
    }

    public Cell getCell(String cellName)
            throws IndexOutOfBoundsException {

        return new Cell(
            getCellRangeByName(cellName)
                .getCellByPosition(0, 0)
        );
    }
}
```

This keeps the automatically generated UNO wrapper separate from the higher-level API.

## Property access

LibreOffice UNO makes extensive use of `XPropertySet`.

When a generated proxy includes `XPropertySet`, LOJ adds a convenience method named `prop()`.

Instead of:

```java
range.setPropertyValue("CellBackColor", color);
range.setPropertyValue("CellStyle", "My Style");
```

properties can be written fluently:

```java
range
    .prop("CellBackColor", color)
    .prop("CellStyle", "My Style");
```

This is particularly useful when applying several formatting properties.

## Higher-level Calc API

The sample project contains higher-level classes built on top of the generated proxies:

```text
Doc
Sheet
Range
Cell
Prop
```

These provide a more natural Java API for manipulating spreadsheets.

### Accessing cells and ranges

```java
Sheet sheet = doc.getSheet(0);

Cell cell = sheet.getCell("A1");

Range range = sheet.getRange("A1:D10");
```

### Writing cells

Cells can be manipulated using a small fluent API:

```java
Cell cell = sheet.getCell("A1");

cell.set("JAN")
    .right()
    .set("FEB")
    .right()
    .set("MAR");
```

This allows spreadsheet operations to be expressed in a form close to the way the spreadsheet itself is structured.

### Working with ranges

```java
Range range = sheet.getRange("A2:D5");

range.prop("CellBackColor", 0xAAAAAA);
```

Ranges can also be copied or used when inserting rows and columns.

```java
Range source = sheet.getRange("A3:D3");

sheet.insertAndCopyRow(5, source);
```

## Charts

LOJ can also manipulate LibreOffice charts through generated UNO proxies.

For example:

```java
P_TableCharts charts =
    new P_TableCharts(sheet.getCharts());

charts.addNewByName(
    "Example",
    rectangle,
    addresses,
    true,
    true
);

P_TableChart tableChart =
    new P_TableChart(charts.getByName("Example"));

P_Chart chart =
    new P_Chart(tableChart.getEmbeddedObject());
```

UNO properties can then be modified using the helper API:

```java
Prop.of(chart.getDiagram())
    .set("Dim3D", Boolean.TRUE);

Prop.of(chart.getTitle())
    .set("String", "The new title");
```

## Streams and document conversion

`loj-util` provides adapters between Java streams and LibreOffice UNO streams.

This makes it possible to load or export documents without requiring ordinary filesystem-based input/output.

For example, a document can be exported through an `OutputStream`.

```java
doc.writeToStream(outputStream, "calc_pdf_Export");
```

This is useful when LibreOffice is used as a document-processing backend in a server application.

## Spring Boot sample

`loj-sb-sample` demonstrates using LOJ from a Spring Boot application.

The sample includes:

- Spreadsheet manipulation
- Cell and range operations
- Styles
- Charts
- Sheet copying and importing
- Document export
- LibreOffice connection pooling

The overall architecture is roughly:

```text
Spring Boot application
        |
        v
 Doc / Sheet / Range / Cell
        |
        v
 Generated P_* proxy classes
        |
        v
 LibreOffice UNO API
        |
        v
 Socket / Pipe connection
        |
        v
 LibreOffice
```

## Modules

```text
loj
├── loj-util
│   ├── connector
│   ├── pool
│   ├── streams
│   └── tempuserdir
│
├── loj-proxy
│   └── UNO proxy source generator
│
└── loj-sb-sample
    ├── generated proxy classes
    ├── high-level Calc classes
    └── Spring Boot sample
```

## Requirements

- Java 11 or later
- Maven
- LibreOffice

The project currently depends on:

```xml
<dependency>
    <groupId>org.libreoffice</groupId>
    <artifactId>libreoffice</artifactId>
    <version>7.2.5</version>
</dependency>
```

## Build

Clone the repository:

```bash
git clone https://github.com/ns2j/loj.git
cd loj
```

Build with Maven:

```bash
mvn package
```

## Design philosophy

The UNO API is powerful and flexible, but its generic interface-oriented architecture can result in verbose Java code.

LOJ separates the problem into layers:

```text
LibreOffice UNO
       |
       | automatic wrapping
       v
Generated proxy classes
       |
       | application-friendly abstraction
       v
Doc / Sheet / Range / Cell
       |
       v
Application code
```

The proxy generator handles the mechanical parts of UNO interface delegation, while the higher-level classes provide APIs that are easier to read and write.

The goal is not to replace UNO, but to make UNO programming feel more like ordinary Java programming.

## License

Apache License 2.0.
