/*
 * FHIRWork (c) 2018 - Blair Butterworth, Abdul-Qadir Ali, Xialong Chen,
 * Chenghui Fan, Alperen Karaoglu, Jiaming Zhou
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package org.ucl.fhirwork.common.network.exception;

public class AmbiguousResultException extends NetworkException
{
    public AmbiguousResultException(String message)
    {
        super(message);
    }

    public AmbiguousResultException(String type, String search)
    {
        this("Ambiguous results for " + type + " search: " + search);
    }
}
