package eel.kitchen.jsonschema.validators.format;

import org.codehaus.jackson.JsonNode;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public final class EmailFormatValidator
    extends AbstractFormatValidator
{
    public EmailFormatValidator(final JsonNode ignored)
    {
        super(ignored);
    }

    @Override
    public boolean validate(final JsonNode node)
    {
        validationErrors.clear();

        try {
            new InternetAddress(node.getTextValue());
            return true;
        } catch (AddressException e) {
            validationErrors.add("string is not a valid email address");
            return false;
        }
    }
}