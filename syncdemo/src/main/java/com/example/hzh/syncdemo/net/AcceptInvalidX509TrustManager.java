/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.example.hzh.syncdemo.net;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class AcceptInvalidX509TrustManager implements X509TrustManager {

  /**
   * @see X509TrustManager#checkClientTrusted(X509Certificate[],
   *      String authType)
   */
  public void checkClientTrusted(X509Certificate[] certificates,
      String authType) throws CertificateException {

  }

  /**
   * @see X509TrustManager#checkServerTrusted(X509Certificate[],
   *      String authType)
   */
  public void checkServerTrusted(X509Certificate[] certificates,
      String authType) throws CertificateException {

  }

  /**
   * @see X509TrustManager#getAcceptedIssuers()
   */
  public X509Certificate[] getAcceptedIssuers() {
    return null;
  }

}
