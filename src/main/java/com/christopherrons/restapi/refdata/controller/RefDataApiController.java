package com.christopherrons.restapi.refdata.controller;

import com.christopherrons.refdata.instrument.enums.InstrumentTypeEnum;
import com.christopherrons.restapi.refdata.dto.ApiAvailableInstrumentTypesDto;
import com.christopherrons.restapi.refdata.dto.ApiAvailableInstrumentsByTypeDto;
import com.christopherrons.restapi.refdata.dto.ApiAvailableInstrumentsDto;
import com.christopherrons.restapi.refdata.requests.ApiAvailableInstrumentsByTypeRequest;
import com.christopherrons.restapi.refdata.service.RefDataApiService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@CrossOrigin("*")
@RequestMapping("/referenceData")
public class RefDataApiController {

    private static final Logger LOGGER = Logger.getLogger(RefDataApiController.class.getName());

    @Autowired
    private RefDataApiService refDataApiService;

    @GetMapping("/availableInstruments")
    @ApiOperation(value = "Request available instruments.",
            notes = "This method returns all available instruments.")
    public ApiAvailableInstrumentsDto getAvailableInstrumentsRequest() {
        LOGGER.info("Get request available instruments received.");
        return refDataApiService.getAvailableInstruments();
    }

    @GetMapping("/availableInstrumentTypes")
    @ApiOperation(value = "Request available instruments types.",
            notes = "This method returns all available instruments types.")
    public ApiAvailableInstrumentTypesDto getAvailableInstrumentTypesRequest() {
        LOGGER.info("Get request available instruments received.");
        return refDataApiService.getAvailableInstrumentTypes();
    }

    @PostMapping(value = "/availableInstrumentsByType")
    @ApiOperation(value = "Request available instruments by type.",
            notes = "This methods returns all available instruments by typ")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instrumentType", defaultValue = "STOCK", dataTypeClass = InstrumentTypeEnum.class, required = true),
    })
    public ApiAvailableInstrumentsByTypeDto subscriptionTradingPairsRequest(final ApiAvailableInstrumentsByTypeRequest availableInstrumentsByTypeRequest) {
        LOGGER.info(String.format("Post request available instruments by type received: %s.", availableInstrumentsByTypeRequest));
        return refDataApiService.getAvailableInstrumentsByType(availableInstrumentsByTypeRequest.getInstrumentType());
    }
}
