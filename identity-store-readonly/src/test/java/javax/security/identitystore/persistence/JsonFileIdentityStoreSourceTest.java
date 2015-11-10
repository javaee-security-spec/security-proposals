/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2015 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package javax.security.identitystore.persistence;

import org.junit.Test;

import javax.security.identitystore.persistence.cachedsource.CachedIdentityStoreSource;
import javax.security.identitystore.persistence.cachedsource.JsonFileIdentityStoreSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the JSON file source
 * {@link javax.security.identitystore.persistence.cachedsource.JsonFileIdentityStoreSource}.
 * This would supply identity data to <code>{@link CachedIdentityStore}</code>.
 */
public class JsonFileIdentityStoreSourceTest {

    @Test
    public void iterator() throws IOException {
        URL resource = getClass().getClassLoader().getResource("identitystore/testIdStore.json");
        if (null == resource)
            throw new NullPointerException("\"identitystore/testIdStore.json\" not in classpath.");

        String idStoreFilePath = resource.getFile();
        File idStoreFile = new File(idStoreFilePath);

        JsonFileIdentityStoreSource source = new JsonFileIdentityStoreSource(idStoreFile);

        Iterator<CachedIdentityStoreSource.CallerSource> iterator = source.getCallerIterator();

        while (iterator.hasNext()) {
            CachedIdentityStoreSource.CallerSource caller = iterator.next();
            System.out.println("Caller:");
            System.out.println(caller.toString());
            assertNotNull("Caller name", caller.getName());
        }
    }
}
