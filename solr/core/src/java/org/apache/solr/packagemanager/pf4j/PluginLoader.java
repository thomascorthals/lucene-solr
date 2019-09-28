/*
 * Copyright 2012 Decebal Suiu
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
package org.apache.solr.packagemanager.pf4j;

import java.nio.file.Path;

/**
 * Load all information (classes) needed by a plugin.
 *
 * @author Decebal Suiu
 */
public interface PluginLoader {

    /**
     * Returns true if this loader is applicable to the given {@link Path}.
     *
     */
    boolean isApplicable(Path pluginPath);

    ClassLoader loadPlugin(Path pluginPath, PluginDescriptor pluginDescriptor);

}