/*
 * Copyright (c) 2012, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.format;

import com.fasterxml.jackson.databind.JsonNode;
import org.eel.kitchen.jsonschema.util.JsonLoader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public abstract class AbstractFormatSpecifierTest
{
    private final FormatSpecifier specifier;

    private final JsonNode testData;

    AbstractFormatSpecifierTest(final FormatSpecifier specifier,
        final String resourceName)
        throws IOException
    {
        this.specifier = specifier;

        testData = JsonLoader.fromResource("/format/" + resourceName + ".json");
    }

    @DataProvider
    protected Iterator<Object[]> getData()
    {
        final Set<Object[]> set = new HashSet<Object[]>();

        for (final JsonNode node: testData)
            set.add(new Object[] {
                node.get("data"),
                node.get("valid").booleanValue()
            }
            );

        return set.iterator();
    }

    @Test(
        dataProvider = "getData"
    )
    public void testSpecifier(final JsonNode data, final boolean valid)
    {
        final List<String> messages = new ArrayList<String>();
        specifier.checkValue(messages, data);

        assertEquals(messages.isEmpty(), valid);
    }
}
