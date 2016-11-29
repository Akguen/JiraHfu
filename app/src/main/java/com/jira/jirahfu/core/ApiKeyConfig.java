package com.jira.jirahfu.core;

/**
 * Konstanten zur Festlegung benoetigter Parameter-Schluesseln
 * fuer JSON-Objekte.
 * => Unterstuetzen die Kommunikation zwischen mobile Applikation
 * und dem JIRA-System.
 *
 * Benoetigte Parameter und Schluessel-Werte werden fuer verschiedene
 * Anfragen von JIRA vorgegeben.
 * @see {@link  <a href="https://docs.atlassian.com/jira/REST/cloud/">
 *     JIRA REST API Reference</a>}
 *
 * @author Gonca Akguen
 * @version 1.0
 * @since 11/2016 (WS16/17)
 */

public class ApiKeyConfig {

    /**
     * Auth
     */
    public static final String KEY_USERNAME ="username";
    public static final String KEY_PASSWORD ="password";

    /**
     * Session
     */
    public static final String KEY_SESSION_OBJECT ="session";
    public static final String KEY_SESSION_NAME ="name";
    public static final String KEY_SESSION_VALUE ="value";
    public static final String KEY_COOKIE ="cookie";


    /**
     * Project
     */
    public static final String KEY_PROJECT_ID ="id";
    public static final String KEY_PROJECT_TYPE_KEY ="projectTypeKey";
    public static final String KEY_PROJECT_KEY ="key";
    public static final String KEY_PROJECT_NAME ="name";
    public static final String KEY_PROJECT_LEAD ="lead";
    public static final String KEY_PROJECT_AVATAR_OBJECT="avatarUrls";
    public static final String KEY_PROJECT_AVATAR_URL_4848="48x48";
    public static final String KEY_PROJECT_AVATAR_URL_2424="24x24";
    public static final String KEY_PROJECT_AVATAR_URL_1616="16x16";
    public static final String KEY_PROJECT_AVATAR_URL_3232="32x32";

}
