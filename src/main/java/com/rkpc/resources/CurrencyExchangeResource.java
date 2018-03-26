package com.rkpc.resources;

import com.rkpc.Service.CurrencyService;
import com.rkpc.model.CurrencyObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/currency")
public class CurrencyExchangeResource {
    CurrencyService service = new CurrencyService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{base}/{value}")
    public CurrencyObject getLatesCurrency(@PathParam("base") String base, @PathParam("value") double value) {
        return service.exchangeCurrency(value, base);
    }
}
