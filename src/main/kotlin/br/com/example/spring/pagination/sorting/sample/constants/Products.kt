package br.com.example.spring.pagination.sorting.sample.constants

import br.com.example.spring.pagination.sorting.sample.model.entities.ProductEntity
import java.math.BigDecimal
import java.math.BigInteger

class Products {
    companion object {
        val productCatalog: List<ProductEntity> = listOf(
            ProductEntity(
                BigInteger.valueOf(1L),
                "Notebook Dell",
                "Notebook Ultrafino Dell Inspiron i5402-M10S 14 Full HD 11ª Geração Intel Core i5",
                BigDecimal(5000)
            ),
            ProductEntity(
                BigInteger.valueOf(2L),
                "Smartphone Samsung Galaxy A12",
                "Smartphone Samsung Galaxy A12 64GB 4G Wi-Fi Tela 6.5'' Dual Chip 4GB RAM Câmera Quádrupla + Selfie 8MP",
                BigDecimal(1100)
            ),
            ProductEntity(
                BigInteger.valueOf(3L),
                "Chandon Réserve Brut",
                "Chandon Réserve Brut 750 ml",
                BigDecimal(70)
            ),
            ProductEntity(
                BigInteger.valueOf(4L),
                "Smartphone Samsung Galaxy M52 5g",
                "Smartphone Samsung Galaxy M52 5g 128gb 5g Wi-Fi Tela 6.7'' Dual Chip 6gb Ram Câmera Tripla + Selfie 32mp",
                BigDecimal(1800)
            ),
            ProductEntity(
                BigInteger.valueOf(5L),
                "Smart Tv 50 Uhd Samsung 4k 50au7700",
                "Smart Tv 50 Uhd Samsung 4k 50au7700 Processador Crystal 4k Tela Sem Limites Visual Livre De Cabos Alexa Built In",
                BigDecimal(2700)
            ),
            ProductEntity(
                BigInteger.valueOf(6L),
                "Cafeteira Expresso Oster Cappuccino",
                "Cafeteira Expresso Oster Cappuccino Bvstecmp65r 1,2l 1170w - Vermelha - 110v",
                BigDecimal(760)
            )
        )
    }
}
