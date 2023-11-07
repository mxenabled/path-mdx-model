package com.mx.testing

import com.mx.path.core.context.Session
import com.mx.path.core.context.facility.Facilities

class Utils {
  static injectSessionEncryptionService() {
    def encryptionService = new TestEncryptionService()
    Session.setEncryptionServiceSupplier({ -> encryptionService })
  }

  static resetSessionEncryptionService() {
    Session.setEncryptionServiceSupplier(null)
  }

  static setupStores(def clientId) {
    Facilities.setCacheStore(clientId, new HashStore())
    Facilities.setSessionStore(clientId, new HashStore())
  }
}
