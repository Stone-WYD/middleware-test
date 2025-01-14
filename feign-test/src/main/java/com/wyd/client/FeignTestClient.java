package com.wyd.client;

import com.wyd.common.AjaxResult;
import com.wyd.common.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xh
 * @date 2025-01-05
 * @Description:
 */
@FeignClient(name = "arthas-test"/*, url = "${test.feign.url}"*/)
public interface FeignTestClient {

    @PostMapping("/arthas/leak/feign")
    AjaxResult<UserEntity> feignTest(@RequestParam("name") String name, @RequestParam("id") Long id);
}
