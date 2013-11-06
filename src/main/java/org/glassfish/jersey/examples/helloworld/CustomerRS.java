/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. Copyright (c)
 * 2010-2012 Oracle and/or its affiliates. All rights reserved. The contents of
 * this file are subject to the terms of either the GNU General Public License
 * Version 2 only ("GPL") or the Common Development and Distribution
 * License("CDDL") (collectively, the "License"). You may not use this file
 * except in compliance with the License. You can obtain a copy of the License
 * at http://glassfish.java.net/public/CDDL+GPL_1_1.html or
 * packager/legal/LICENSE.txt. See the License for the specific language
 * governing permissions and limitations under the License. When distributing
 * the software, include this License Header Notice in each file and include the
 * License file at packager/legal/LICENSE.txt. GPL Classpath Exception: Oracle
 * designates this particular file as subject to the "Classpath" exception as
 * provided by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. Modifications: If applicable, add the following below
 * the License Header, with the fields enclosed by brackets [] replaced by your
 * own identifying information:
 * "Portions Copyright [year] [name of copyright owner]" Contributor(s): If you
 * wish your version of this file to be governed by only the CDDL or only the
 * GPL Version 2, indicate your decision by adding "[Contributor] elects to
 * include this software in this distribution under the [CDDL or GPL Version 2]
 * license." If you don't indicate a single choice of license, a recipient has
 * the option to distribute your version of this file under either the CDDL, the
 * GPL Version 2 or to extend the choice of license to its licensees as provided
 * above. However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is made
 * subject to such option by the copyright holder.
 */
package org.glassfish.jersey.examples.helloworld;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.PUT;
// import javax.ws.rs.Inject;
// import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

public class CustomerRS {

    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";

    @Inject
    CustomerStore customerStore;

    @GET
    @Produces("text/plain")
    public String getUser(@PathParam("username") String userName) {

        Customer reqCust = customerStore.getCustomer(userName);
        if (reqCust != null) {
            return reqCust.toJson();
        }
        return "No Such User";
    }

    @PUT
    public Response putCustomer(String content, @PathParam("username") String userName) {

        Customer reqCust = parseRequest(content, userName);
        customerStore.processCustomer(reqCust);
        return Response.ok("Recieved:" + reqCust.getFirstName()).build();
    }

    @Target({ ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    @HttpMethod("PATCH")
    public @interface PATCH {
    }

    @PATCH 
    public Response patchCustomer(String content, @PathParam("username") String userName) {

        Customer reqCust = parseRequest(content, userName);
        boolean success = customerStore.patchCustomer(reqCust);
        if (success) {
            return Response.ok().build();
        }
        return Response.status(404).build();
    }

    private Customer parseRequest(String content, String userName) {

        String firstName = null;
        String lastName = null;
        String phoneNumber = null;
        String eMail = null;

        JSONObject jsonObj = new JSONObject(content);
        if (!jsonObj.isNull(FIRST_NAME)) {
            firstName = jsonObj.getString(FIRST_NAME);
        }
        if (!jsonObj.isNull(LAST_NAME)) {
            lastName = jsonObj.getString(LAST_NAME);
        }
        if (!jsonObj.isNull(PHONE_NUMBER)) {
            phoneNumber = jsonObj.getString(PHONE_NUMBER);
        }
        if (!jsonObj.isNull(EMAIL)) {
            eMail = jsonObj.getString(EMAIL);
        }
        Customer reqCust = new Customer(firstName, lastName, phoneNumber, eMail, userName);
        return reqCust;
    }

}
