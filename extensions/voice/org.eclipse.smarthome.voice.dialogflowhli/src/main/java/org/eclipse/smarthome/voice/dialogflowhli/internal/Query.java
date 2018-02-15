package org.eclipse.smarthome.voice.dialogflowhli.internal;

public class Query {

    private String lang;
    private String query;
    private String timezone;
    private String sessionId;

    public Query(String query, String lang, String timezone, String sessionId) {
        this.setQuery(query);
        this.setLang(lang);
        this.setTimezone(timezone);
        this.setSessionId(sessionId);
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
