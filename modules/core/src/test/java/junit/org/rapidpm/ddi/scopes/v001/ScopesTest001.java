/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package junit.org.rapidpm.ddi.scopes.v001;

import junit.org.rapidpm.ddi.DDIBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.ddi.DI;
import org.rapidpm.ddi.scopes.InjectionScope;
import org.rapidpm.ddi.scopes.InjectionScopeManager;
import org.rapidpm.ddi.scopes.provided.JVMSingletonInjectionScope;

import java.util.Set;

public class ScopesTest001 extends DDIBaseTest {


  @Test
  public void test001() throws Exception {
    final SingletonTestClass singletonTestClassA = DI.activateDI(SingletonTestClass.class);
    final SingletonTestClass singletonTestClassB = DI.activateDI(SingletonTestClass.class);

    Assertions.assertNotNull(singletonTestClassA);
    Assertions.assertNotNull(singletonTestClassB);
    Assertions.assertNotEquals(singletonTestClassA, singletonTestClassB);


    DI.registerClassForScope(SingletonTestClass.class, JVMSingletonInjectionScope.class.getSimpleName());

    final SingletonTestClass singletonTestClassC = DI.activateDI(SingletonTestClass.class);
    final SingletonTestClass singletonTestClassD = DI.activateDI(SingletonTestClass.class);

    Assertions.assertNotNull(singletonTestClassC);
    Assertions.assertNotNull(singletonTestClassD);

    Assertions.assertNotEquals(singletonTestClassA, singletonTestClassC);
    Assertions.assertNotEquals(singletonTestClassA, singletonTestClassD);

    Assertions.assertNotEquals(singletonTestClassB, singletonTestClassC);
    Assertions.assertNotEquals(singletonTestClassB, singletonTestClassD);

    Assertions.assertEquals(singletonTestClassC, singletonTestClassD);


    final String scopeForClass = InjectionScopeManager.scopeForClass(SingletonTestClass.class);
    Assertions.assertEquals(JVMSingletonInjectionScope.class.getSimpleName(), scopeForClass);


    DI.deRegisterClassForScope(SingletonTestClass.class);
    final SingletonTestClass singletonTestClassE = DI.activateDI(SingletonTestClass.class);
    Assertions.assertNotEquals(singletonTestClassE, singletonTestClassA);
    Assertions.assertNotEquals(singletonTestClassE, singletonTestClassB);
    Assertions.assertNotEquals(singletonTestClassE, singletonTestClassC);
    Assertions.assertNotEquals(singletonTestClassE, singletonTestClassD);

  }


  @Test
  public void test002() throws Exception {
    DI.registerClassForScope(SingletonTestClass.class, JVMSingletonInjectionScope.class.getSimpleName());

    Assertions.assertTrue(InjectionScopeManager.isManagedByMe(SingletonTestClass.class));
    Assertions.assertFalse(InjectionScopeManager.isManagedByMe(NonSingletonTestClass.class));

    Assertions.assertEquals("PER INJECT", InjectionScopeManager.scopeForClass(NonSingletonTestClass.class));
  }

  @Test
  public void test003() throws Exception {

    final String singeltonScopeName = JVMSingletonInjectionScope.class.getSimpleName();
    DI.registerClassForScope(SingletonTestClass.class, singeltonScopeName);

    Assertions.assertTrue(InjectionScopeManager.isManagedByMe(SingletonTestClass.class));
    Assertions.assertFalse(InjectionScopeManager.isManagedByMe(NonSingletonTestClass.class));

    Assertions.assertEquals("PER INJECT", InjectionScopeManager.scopeForClass(NonSingletonTestClass.class));

    final Set<String> strings = InjectionScopeManager.listAllActiveScopeNames();
    Assertions.assertTrue(strings.contains(TestScope.class.getSimpleName()));

    final SingletonTestClass singletonTestClassA = DI.activateDI(SingletonTestClass.class);

    InjectionScopeManager.clearScope(singeltonScopeName);

    final SingletonTestClass singletonTestClassB = DI.activateDI(SingletonTestClass.class);
    Assertions.assertNotEquals(singletonTestClassA, singletonTestClassB);

    final SingletonTestClass singletonTestClassC = DI.activateDI(SingletonTestClass.class);
    Assertions.assertNotEquals(singletonTestClassA, singletonTestClassB);
    Assertions.assertNotEquals(singletonTestClassA, singletonTestClassC);


    Assertions.assertEquals(singletonTestClassB, singletonTestClassC);

  }


  public static class SingletonTestClass {
  }

  public static class NonSingletonTestClass {
  }


  public static class TestScope extends InjectionScope {
    @Override
    public <T> T getInstance(final String clazz) {
      return null;
    }

    @Override
    public <T> void storeInstance(final Class<T> targetClassOrInterface, final T instance) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getScopeName() {
      return TestScope.class.getSimpleName();
    }
  }


}
