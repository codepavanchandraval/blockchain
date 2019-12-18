/*******************************************************************************
 * BlujaySolutions ("COMPANY") CONFIDENTIAL
 *
 * Unpublished Copyright (c) 2015-2018 BlujaySolutions, All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of
 * COMPANY. The intellectual and technical concepts contained herein are
 * proprietary to COMPANY and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from COMPANY.
 * Access to the source code contained herein is hereby forbidden to anyone
 * except current, authorized COMPANY employees, managers or contractors who
 * have executed Confidentiality and Non-disclosure agreements explicitly
 * covering such access.
 *
 * The copyright notice above does not evidence any actual or intended
 * publication or disclosure of this source code, which includes information
 * that is confidential and/or proprietary, and is a trade secret, of COMPANY.
 *
 * ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE, OR PUBLIC
 * DISPLAY OF OR THROUGH USE OF THIS SOURCE CODE WITHOUT THE EXPRESS WRITTEN
 * CONSENT OF COMPANY IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES. THE RECEIPT OR POSSESSION OF THIS SOURCE
 * CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS TO
 * REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR
 * SELL ANYTHING THAT IT MAY DESCRIBE, IN WHOLE OR IN PART.
 *******************************************************************************/
/* 
  * Purpose : To remove the cross-scripting content from the URL.  
 * */
package com.pavan.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SecurityWrapper extends HttpServletRequestWrapper {
    HttpServletRequest servletRequest = null;

    private static Pattern[] patterns = new Pattern[] { Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("<(.*?)/(.*?)>", Pattern.CASE_INSENSITIVE), Pattern.compile("<(.*?)>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("</(.*?)>", Pattern.CASE_INSENSITIVE),
            Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE), Pattern.compile("(.?)([\\s][s][r][c][\\s]*[\r\n]*)(.*?)", Pattern.CASE_INSENSITIVE),
            Pattern.compile("onMouseOver(.*?)=(.*?)\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL) };

    public SecurityWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
        this.servletRequest = servletRequest;
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(values[i]);
        }
        return encodedValues;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> paramNames = super.getParameterNames();
        List<String> paramList = new ArrayList<String>();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            paramName = stripXSS(paramName);
            paramList.add(paramName);
        }
        return Collections.enumeration(paramList);
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        value = stripXSS(value);
        return value;
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(value);
    }

    private String stripXSS(String value) {

        if (value != null) {
            if (value.contains("\0")) {
                value = value.replaceAll("\0", "");
            }
            for (Pattern scriptPattern : patterns) {
                value = scriptPattern.matcher(value).replaceAll("");
            }
            value = value.replaceAll("[<>\";]", "");

            if (value.contains("$@$")) {
                value = value.replaceAll("$@$", "'");
            }
            if (value.contains("\'\'")) {
                value = value.replaceAll("\'\'", "\'");
            }
            if (value.contains("../") || value.contains("./")) {
                value = value.replaceAll("../", "").replaceAll("./", "");
            }
        }
        return value;
    }

}