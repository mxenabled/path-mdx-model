package com.mx.path.extensions

class StringStaticExtension {

  static String sanitizeXml(final StringWriter self) {
    sanitizeXml(self.toString())
  }

  static String sanitizeXml(final String self) {
    self.replaceAll("\n","").replaceAll("\r","").replaceAll("\t","").replaceAll(">\\s+<", "")
  }
}
