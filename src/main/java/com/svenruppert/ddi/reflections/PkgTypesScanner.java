/**
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
package com.svenruppert.ddi.reflections;

import org.reflections8.scanners.AbstractScanner;
import org.reflections8.util.FilterBuilder;
import org.reflections8.util.SetMultimap;


public class PkgTypesScanner extends AbstractScanner {

  /**
   * created new SubTypesScanner. will exclude direct Object subtypes
   */
  public PkgTypesScanner() {
    this(true); //exclude direct Object subtypes by default
  }

  /**
   * created new SubTypesScanner.
   *
   * @param excludeObjectClass if false, include direct {@link Object} subtypes in results.
   */
  public PkgTypesScanner(boolean excludeObjectClass) {
    if (excludeObjectClass) {
      filterResultsBy(new FilterBuilder().exclude(Object.class.getName())); //exclude direct Object subtypes
    }
  }

  @SuppressWarnings({"unchecked"})
  public void scan(final Object cls) {
    String className = getMetadataAdapter().getClassName(cls);

    int index = className.lastIndexOf(".");
    if (index != -1) {

      final String pkgName = className.substring(0, index);
      if (acceptResult(className)) {
        final SetMultimap<String, String> store = getStore();
        store.putSingle(pkgName, className);
      }
    }
  }
}
