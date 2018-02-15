/**
smar * Copyright (c) 2014,2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.voice.dialogflowhli;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.i18n.TimeZoneProvider;
import org.eclipse.smarthome.core.voice.text.HumanLanguageInterpreter;
import org.eclipse.smarthome.core.voice.text.InterpretationException;
import org.eclipse.smarthome.io.net.http.HttpUtil;
import org.eclipse.smarthome.voice.dialogflowhli.internal.DialogFlowConfiguration;
import org.eclipse.smarthome.voice.dialogflowhli.internal.Query;
import org.eclipse.smarthome.voice.dialogflowhli.internal.QueryResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import com.google.gson.Gson;

/**
 * A human language command interpretation service.
 *
 * @author Daniel Walters - Initial contribution and API
 *
 */

@Component(service = HumanLanguageInterpreter.class, immediate = true, configurationPid = "voice.dialogflow", configurationPolicy = ConfigurationPolicy.OPTIONAL)
public class DialogflowInterpreter implements HumanLanguageInterpreter {

    private final String BASE_URL = "https://api.dialogflow.com/v1/";
    private final String QUERY_URL = BASE_URL + "query?v=20150910";
    private TimeZoneProvider timeZoneProvider;

    @Nullable
    private DialogFlowConfiguration config;

    @Override
    public String getId() {
        return "dialogflow";
    }

    @Override
    public String getLabel(Locale locale) {
        return "Dialogflow Interpreter HLI";
    }

    @Override
    public String interpret(Locale locale, String text) throws InterpretationException {

        String language = locale.getLanguage();
        Gson gson = new Gson();
        String queryText = "Turn off the bedroom light";
        String timezone = timeZoneProvider.getTimeZone().toString();
        String sessionId = "12345";
        Query q = new Query(queryText, language, timezone, sessionId);
        String jsonPayload = gson.toJson(q);
        String contentType = "application/json";
        Properties httpHeaders = new Properties();
        String key = "b6d91f2a1e9d486d8a18c2f7f298aca7";
        httpHeaders.setProperty("Authorization", "Bearer " + key);
        InputStream stream = new ByteArrayInputStream(jsonPayload.getBytes(StandardCharsets.UTF_8));
        String response = null;
        try {
            response = HttpUtil.executeUrl("POST", QUERY_URL, httpHeaders, stream, contentType, 30000);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        QueryResponse queryResponse = gson.fromJson(response, QueryResponse.class);
        String x = queryResponse.getResult().getFulfillment().getSpeech();
        return x;
    }

    @Override
    public String getGrammar(Locale locale, String format) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Locale> getSupportedLocales() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> getSupportedGrammarFormats() {
        // TODO Auto-generated method stub
        return null;
    }

    @Reference
    protected void setTimeZoneProvider(TimeZoneProvider timeZoneProvider) {
        this.timeZoneProvider = timeZoneProvider;
    }

    protected void unsetTimeZoneProvider(TimeZoneProvider timeZoneProvider) {
        this.timeZoneProvider = null;
    }

}
