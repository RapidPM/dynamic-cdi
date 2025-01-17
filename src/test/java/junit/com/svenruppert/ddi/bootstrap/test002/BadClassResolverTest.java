/*
 * Copyright © 2013 Sven Ruppert (sven.ruppert@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package junit.com.svenruppert.ddi.bootstrap.test002;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.svenruppert.ddi.DDIModelException;
import com.svenruppert.ddi.DI;
import com.svenruppert.ddi.implresolver.ClassResolver;
import junit.com.svenruppert.ddi.DDIBaseTest;

/**
 *
 */
public class BadClassResolverTest extends DDIBaseTest {

  public static class DemoClass {}

  public static class BadClassResolver implements ClassResolver<DemoClass> {
    @Override
    public Class<? extends DemoClass> resolve(Class<DemoClass> interf) {
      return DemoClass.class;
    }
  }

  @Test()
  public void test001() {
    Assertions.assertThrows(DDIModelException.class, DI::checkActiveModel);
  }
}
