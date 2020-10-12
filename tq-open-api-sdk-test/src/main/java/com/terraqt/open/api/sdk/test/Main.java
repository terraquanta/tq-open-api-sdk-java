package com.terraqt.open.api.sdk.test;

import com.terraqt.open.api.sdk.api.BatchService;
import com.terraqt.open.api.sdk.api.DataService;
import com.terraqt.open.api.sdk.api.ServiceService;
import com.terraqt.open.api.sdk.config.OpenApiConfig;
import com.terraqt.open.api.sdk.dto.batch.BatchDto;
import com.terraqt.open.api.sdk.dto.common.CdpDto;
import com.terraqt.open.api.sdk.dto.service.ServiceDetailDto;
import com.terraqt.open.api.sdk.dto.service.ServiceDto;
import com.terraqt.open.api.sdk.dto.stats.StatsDto;
import com.terraqt.open.api.sdk.util.ApiUtil;
import com.terraqt.open.api.sdk.util.json.JsonHelper;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        OpenApiConfig.configure("160ef-d-8adf1b95", "0e4325d043c54fbbbd49d936fcc14227");
        ServiceService serviceService = ApiUtil.getApi(ServiceService.class);
        BatchService batchService = ApiUtil.getApi(BatchService.class);
        DataService dataService = ApiUtil.getApi(DataService.class);

        List<ServiceDto> serviceDtos = serviceService.getAllServices();
        serviceDtos.forEach(serviceDto -> {
            System.out.println(serviceDto.getName());
            ServiceDetailDto serviceDetail = serviceService.getServiceDetail(serviceDto.getId());
            List<CdpDto> cdpDtos = serviceDetail.getCdps();
            cdpDtos.forEach(cdpDto -> {
                System.out.println("\t" + cdpDto.getCode());
                List<BatchDto> batches = batchService.getBatches(cdpDto.getId());
                batches.forEach(batch -> {
                    System.out.println("\t\t" + batch.getBatchId());
                    String tiffUrl = dataService.getTiff(batch.getBatchId());
                    System.out.println("\t\t\t" + tiffUrl);

                    List<StatsDto> statsDto = dataService.getStats(batch.getBatchId());
                    System.out.println("\t\t\t" + JsonHelper.writeAsString(statsDto));

                    String geo = dataService.getGeo(batch.getBatchId());
                    System.out.println("\t\t\t" + geo);
                });
            });
        });
        System.out.println(1);
    }
}
