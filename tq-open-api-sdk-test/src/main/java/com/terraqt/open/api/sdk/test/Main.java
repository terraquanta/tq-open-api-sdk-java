package com.terraqt.open.api.sdk.test;

import com.terraqt.open.api.sdk.api.BatchService;
import com.terraqt.open.api.sdk.api.DataService;
import com.terraqt.open.api.sdk.api.ServiceService;
import com.terraqt.open.api.sdk.config.OpenApiConfig;
import com.terraqt.open.api.sdk.dto.batch.PaginationBatchDto;
import com.terraqt.open.api.sdk.dto.common.CdpDto;
import com.terraqt.open.api.sdk.dto.common.Paging;
import com.terraqt.open.api.sdk.dto.service.ServiceDetailDto;
import com.terraqt.open.api.sdk.dto.service.ServiceDto;
import com.terraqt.open.api.sdk.dto.stats.StatsDto;
import com.terraqt.open.api.sdk.util.ApiUtil;
import com.terraqt.open.api.sdk.util.json.JsonHelper;

import java.util.List;

public class Main {
    /**
     * Replace with you access key, you can get it from https://app.terraqt.com/app/account?tab=api-keys
     */
    private static final String KEY = "1758f-ch-57895101d";

    /**
     * Replace with you access secret, you can get it from https://app.terraqt.com/app/account?tab=api-keys
     */
    private static final String SECRET = "3b1965f6a1db4ec69ea003b2610aa071c";

    public static void main(String[] args) throws Exception {
        // configure sdk
        OpenApiConfig.configure(KEY, SECRET);

        // get instance of api.
        ServiceService serviceService = ApiUtil.getApi(ServiceService.class);
        BatchService batchService = ApiUtil.getApi(BatchService.class);
        DataService dataService = ApiUtil.getApi(DataService.class);

        List<ServiceDto> services = serviceService.getAllServices();
        services.forEach(service -> {
            System.out.println(service.getName());
            ServiceDetailDto serviceDetail = serviceService.getServiceDetail(service.getId());
            List<CdpDto> cdps = serviceDetail.getCdps();
            cdps.forEach(cdp -> {
                System.out.println("\t" + cdp.getCode());

                final int limit = 100;
                int offset = 0;
                Paging paging = new Paging();
                while (true) {
                    paging.setLimit(limit);
                    paging.setOffset(offset);
                    PaginationBatchDto paginationBatch = batchService.getBatchesWithPaging(cdp.getId(), paging);
                    if (null == paginationBatch.getBatches() || paginationBatch.getBatches().isEmpty()) {
                        break;
                    }
                    offset += paginationBatch.getBatches().size();
                    paginationBatch.getBatches().forEach(batch -> {
                        System.out.println("\t\t" + batch.getBatchId());
                        String tiffUrl = dataService.getTiff(batch.getBatchId());
                        System.out.println("\t\t\t" + tiffUrl);

                        List<StatsDto> statsDto = dataService.getStats(batch.getBatchId());
                        System.out.println("\t\t\t" + JsonHelper.writeAsString(statsDto));

                        String geo = dataService.getGeo(batch.getBatchId());
                        System.out.println("\t\t\t" + geo);

                        String image = dataService.getImage(batch.getBatchId());
                        System.out.println("\t\t\t" + image);
                    });
                }

            });
        });
    }
}
