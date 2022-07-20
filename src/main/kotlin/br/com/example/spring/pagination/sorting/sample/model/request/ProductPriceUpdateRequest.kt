package br.com.example.spring.pagination.sorting.sample.model.request

import java.math.BigDecimal
import javax.validation.constraints.Min

data class ProductPriceUpdateRequest(
    @Min(1L) var price: BigDecimal
)
