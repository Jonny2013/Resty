package cn.dreampie.security;

import cn.dreampie.common.util.Maper;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by wangrenhui on 14/12/23.
 */
public class Session implements Serializable {
  public static final String SESSION_DEF_KEY = "_session";
  private final Map<String, String> values;
  private final String sessionKey;
  private final Principal principal;
  private final long expires;

  public Session() {
    this(UUID.randomUUID().toString(), null, Maper.<String, String>of(), -1);
  }

  public Session(String sessionKey, Principal principal, Map<String, String> values, long expires) {
    this.sessionKey = sessionKey;
    this.principal = principal;
    this.values = values;
    this.expires = expires;
  }

  public String getSessionKey() {
    return sessionKey;
  }

  long getExpires() {
    return expires;
  }

  Principal getPrincipal() {
    return principal;
  }

  Map<String, String> getValues() {
    return values;
  }

  String get(String key) {
    return values.get(key);
  }

  void set(Map<String, String> values) {
    Set<Map.Entry<String, String>> entrySet = values.entrySet();
    for (Map.Entry<String, String> entry : entrySet) {
      set(entry.getKey(), entry.getValue());
    }
  }

  void set(String key, String value) {
    if (value == null) {
      values.remove(key);
    } else {
      values.put(key, value);
    }
  }

  String remove(String key) {
    return values.remove(key);
  }
}
