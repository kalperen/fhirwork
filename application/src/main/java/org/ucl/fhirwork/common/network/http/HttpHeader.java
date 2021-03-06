/*
 * FHIRWork (c) 2018 - Blair Butterworth, Abdul-Qadir Ali, Xialong Chen,
 * Chenghui Fan, Alperen Karaoglu, Jiaming Zhou
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package org.ucl.fhirwork.common.network.http;

/**
 * Options in this enumeration specify identifiers for HTTP header key value
 * pairs.
 *
 * @author Blair Butterworth
 */
public enum HttpHeader
{
    Accept      ("accept"),
    ContentType ("Content-Type");

    private String value;

    HttpHeader(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
