/*
 * Copyright (c) 2011, Francis Galiegue <fgaliegue@gmail.com>
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eel.kitchen.jsonschema.v2.check;

import eel.kitchen.util.NodeType;
import org.codehaus.jackson.JsonNode;

import java.net.URI;
import java.net.URISyntaxException;

abstract class URIKeywordChecker
    extends SingleTypeKeywordChecker
{
    protected URIKeywordChecker(final String fieldName)
    {
        super(fieldName, NodeType.STRING);
    }

    @Override
    public boolean validate(final JsonNode schema)
    {
        if (!super.validate(schema))
            return false;

        try {
            new URI(schema.get(fieldName).getTextValue());
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}