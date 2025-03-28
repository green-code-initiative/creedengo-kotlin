/*
 * creedengo - Kotlin language - Provides rules to reduce the environmental footprint of your Kotlin programs
 * Copyright © 2024 Green Code Initiative (https://green-code-initiative.org/)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.greencodeinitiative.creedengo.kotlin;

import java.util.ArrayList;

import org.sonar.api.SonarRuntime;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;


public class KotlinRulesDefinition implements RulesDefinition {
    private static final String RESOURCE_BASE_PATH = "org/green-code-initiative/rules/kotlin";

    private static final String NAME = "creedengo";
    static final String LANGUAGE = "kotlin";
    static final String REPOSITORY_KEY = "creedengo-kotlin";

    private final SonarRuntime sonarRuntime;

    public KotlinRulesDefinition(SonarRuntime sonarRuntime) {
        this.sonarRuntime = sonarRuntime;
    }

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(REPOSITORY_KEY, LANGUAGE).setName(NAME);

        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(RESOURCE_BASE_PATH, sonarRuntime);

        ruleMetadataLoader.addRulesByAnnotatedClass(repository, new ArrayList<>(KotlinCheckRegistrar.checkClasses()));
        repository.done();
    }

    public String repositoryKey() {
        return REPOSITORY_KEY;
    }
}