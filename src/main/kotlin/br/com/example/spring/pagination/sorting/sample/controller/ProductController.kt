package br.com.example.spring.pagination.sorting.sample.controller

import br.com.example.spring.pagination.sorting.sample.model.request.ProductPriceUpdateRequest
import br.com.example.spring.pagination.sorting.sample.model.request.ProductRequest
import br.com.example.spring.pagination.sorting.sample.model.response.ProductResponse
import br.com.example.spring.pagination.sorting.sample.service.ProductService
import mu.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigInteger
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/v1/products")
class ProductController(
    private val productService: ProductService
) {

    private val log = KotlinLogging.logger {}

    @PostMapping
    private fun createProduct(@RequestBody productRequest: ProductRequest): ProductResponse {
        log.info("createProduct: creating product=${productRequest.name}")
        return productService.createProduct(productRequest).also {
            log.info("createProduct: finished creating product=${productRequest.name}")
        }
    }

    @PutMapping("/{id}")
    private fun updateProduct(
        @PathVariable @Min(1) id: BigInteger,
        @RequestBody productRequest: ProductRequest
    ) {
        log.info("updateProduct: updating product=${productRequest.name}")
        return productService.updateProduct(id, productRequest).also {
            log.info("updateProduct: finished updating product=${productRequest.name}")
        }
    }

    @PatchMapping("/{id}")
    private fun updateProductPrice(
        @PathVariable @Min(1) id: BigInteger,
        @RequestBody productPriceUpdateRequest: ProductPriceUpdateRequest
    ) {
        log.info("updateProductPrice: updating product price=${productPriceUpdateRequest.price}")
        return productService.updateProductPrice(id, productPriceUpdateRequest).also {
            log.info("updateProductPrice: finished updating product price=${productPriceUpdateRequest.price}")
        }
    }

    @GetMapping("/{id}")
    private fun getProduct(@PathVariable @Min(1) id: BigInteger): ProductResponse {
        log.info("getProduct: getting productId=$id")
        return productService.getProduct(id).also {
            log.info("getProduct: finished getting productId=$id")
        }
    }

    @GetMapping
    private fun getProducts(
        @RequestParam(required = false) searchTerm: String,
        @RequestParam(required = false) sortBy: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "3") size: Int
    ): Page<ProductResponse> {
        log.info("getProducts: getting list of products")
        return productService.getProducts(searchTerm, sortBy, page, size).also {
            log.info("getProducts: finished getting list of products")
        }
    }
}
