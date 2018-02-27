/*
 * FHIRWork (c) 2018 - Blair Butterworth, Abdul-Qadir Ali, Xialong Chen,
 * Chenghui Fan, Alperen Karaoglu, Jiaming Zhou
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package org.ucl.fhirwork.mapping.query;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.ucl.fhirwork.configuration.ConfigService;
import org.ucl.fhirwork.configuration.data.ConfigType;
import org.ucl.fhirwork.configuration.data.MappingConfig;
import org.ucl.fhirwork.mapping.query.scripted.ScriptQuantity;
import org.ucl.fhirwork.mapping.query.scripted.ScriptQuery;
import org.ucl.fhirwork.test.TestResourceUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MappingServiceTest
{
    /*
    @Test
    public void getQueryTest()
    {
        ConfigService configuration = getMockConfiguration();
        QueryService queryService = new QueryService(configuration);

        String query = queryService.getQuery("3141-9", "c831fe4d-0ce9-4a63-8bfa-2c51007f97e5");
        Assert.assertNotNull(query);
        Assert.assertEquals(query,
            "select " +
                "body_weight/data[at0002]/origin/value as date, " +
                "body_weight/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as magnitude, " +
                "body_weight/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units as unit " +
            "from EHR [ehr_id/value='c831fe4d-0ce9-4a63-8bfa-2c51007f97e5'] " +
            "contains COMPOSITION c " +
            "contains OBSERVATION body_weight[openEHR-EHR-OBSERVATION.body_weight.v1]");
    }

    private ConfigService getMockConfiguration()
    {
        MappingConfigData configData = new MappingConfigData(
            "body_weight",
            "openEHR-EHR-OBSERVATION.body_weight.v1",
            "data[at0002]/origin/value",
            "data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude",
            "data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units");

        ConfigService configService = Mockito.mock(ConfigService.class);
        MappingConfig mappingConfig = Mockito.mock(MappingConfig.class);

        Mockito.when(mappingConfig.getData("3141-9")).thenReturn(configData);
        Mockito.when(configService.getConfig(ConfigType.Mapping)).thenReturn(mappingConfig);

        return configService;
    }
    */


    @Test
    public void sandboxTest()
    {
        try {
            String script = TestResourceUtils.readResource("mapping/script.js");
            //String script = "function Query(selectors, archetype){    this.selectors = selectors;    this.archetype = archetype;}function Quantity(value, unit){    this.value = value;    this.unit = unit;}function getQuery(ehrId){    var archetype = \"openEHR-EHR-OBSERVATION.skeletal_age.v0\";    var selectors = [\"/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value/value as value\"];    return new Query(selectors, archetype);}function getQuantity(queryResult){    var value = dateToMonths(queryResult.get(\"value\"));    var unit = \"Months\";    return new Quantity(value, unit);}function dateToMonths(date){    return 4.1;}";

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(script);

            Invocable invocable = (Invocable)engine;
            ScriptObjectMirror queryMirror = (ScriptObjectMirror)invocable.invokeFunction("getQuery", "c831fe4d-0ce9-4a63-8bfa-2c51007f97e5");
            ScriptQuery query = new ScriptQuery(queryMirror);

            System.out.println(query.getArchetype());
            System.out.println(query.getSelectors());

            Map<String, String> foo = new HashMap<>();
            foo.put("value", "P6Y");

            ScriptObjectMirror quantityMirror = (ScriptObjectMirror)invocable.invokeFunction("getQuantity", foo);
            ScriptQuantity quantity = new ScriptQuantity(quantityMirror);

            System.out.println(quantity.getUnit());
            System.out.println(quantity.getValue());
        }
        catch (Throwable error){
            error.printStackTrace();
        }
    }
}