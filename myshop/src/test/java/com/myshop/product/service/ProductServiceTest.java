package com.myshop.product.service;

import com.myshop.exceptions.BadIdException;
import com.myshop.product.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductServiceImpl productService;

    //Tests for method Optional<Product> findById(Long id)
    @Test
    public void givenNull_WhenFindById_ThenThrowInvalidIdException(){
        assertThrows(BadIdException.class, () -> productService.getProduct(null));
    }
    @Test
    public void givenZero_WhenFindById_ThenThrowInvalidIdException(){
        assertThrows(BadIdException.class, () -> productService.getProduct(0L));
    }
    @Test
    public void givenNegative_WhenFindById_ThenThrowInvalidIdException(){
        assertThrows(BadIdException.class, () -> productService.getProduct(-1L));
    }



//    //Tests for method List<Product> findAll()
//    @Test
//    public void givenProductsInDb_WhenFindAll_ThenListOfAllProducts(){
//        Product product1 = productService.saveProduct(testProduct);
//        Product product2 = productService.saveProduct(new Product(2L,"name2", "description",
//                new Image("thumbnail.png"), BigDecimal.valueOf(1), 1, ProductStatus.ONLINE));
//        Product product3 = productService.saveProduct(new Product(3L,"name3", "description",
//                new Image("thumbnail.png"), BigDecimal.valueOf(1), 1, ProductStatus.ONLINE));
//
//        assertEquals(List.of(product1, product2 , product3), productService.findAll());
//    }
//    @Test
//    public void givenEmptyDb_WhenFindAll_ThenEmptyList(){
//        assertEquals(List.of(), productService.findAll());
//    }
//
//    //Tests for method Product saveProduct(Product newProduct)
//    @Test
//    public void givenNull_WhenSaveProduct_ThenThrowNewIllegalArgumentException(){
//        assertThrows(IllegalArgumentException.class,() -> productService.saveProduct(null));
//    }
//    @Test
//    public void givenProduct_WhenSaveProduct_ThenThisProduct(){
//
//        assertEquals(testProduct, productService.saveProduct(testProduct));
//    }
//    @Test
//    public void givenProduct_WhenSaveProduct_ThenSaveThisProductInDb(){
//        Product product = productService.saveProduct(testProduct);
//
//        assertTrue(productService.findById(product.getId()).isPresent());
//        assertEquals(product, productService.findById(product.getId()).get());
//    }
//
//
//    //Tests for method Product replaceProduct(Product, Long id)
//    @Test
//    public void givenNullId_WhenReplaceProduct_ThenThrowInvalidIdException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(InvalidIdValueException.class, () -> productService.replaceProduct(product,null));
//    }
//    @Test
//    public void givenZeroId_WhenReplaceProduct_ThenThrowInvalidIdException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(InvalidIdValueException.class, () -> productService.replaceProduct(product,0L));
//    }
//    @Test
//    public void givenNegativeId_WhenReplaceProduct_ThenThrowInvalidIdException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(InvalidIdValueException.class, () -> productService.replaceProduct(product,-1L));
//    }
//    @Test
//    public void givenNull_WhenReplaceProduct_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.replaceProduct(null, product.getProductCode()));
//    }
//    @Test
//    public void givenIdNotInDb_WhenReplaceProduct_ThenSaveNewProductInDb(){
//        Product product = productService.saveProduct(testProduct);
//        Product update = new Product(1L,"name2", "description2",
//                new Image("thumbnail.png"), BigDecimal.valueOf(12), 12, ProductStatus.OFFLINE);
//        productService.replaceProduct(update, product.getProductCode() + 1);
//
//        assertTrue(productService.findById(update.getId()).isPresent());
//        assertEquals("name2", productService.findById(update.getId()).get().getName());
//        assertEquals("description2", productService.findById(update.getId()).get().getDescription());
//        assertEquals(12, productService.findById(update.getId()).get().getPrice().doubleValue());
//        assertEquals(12, productService.findById(update.getId()).get().getQuantity());
//        assertEquals(ProductStatus.OFFLINE, productService.findById(update.getId()).get().getStatus());
//    }
//    @Test
//    public void givenIdDbAndUpdatedProduct_WhenReplaceProduct_ThenReplaceThisProductInDbInDb(){
//        Product product = productService.saveProduct(testProduct);
//        Product update = new Product(2L,"name2", "description2",
//                new Image("thumbnail.png"), BigDecimal.valueOf(12), 12, ProductStatus.OFFLINE);
//        productService.replaceProduct(update, product.getId());
//
//        assertTrue(productService.findById(product.getId()).isPresent());
//        assertEquals("name2", productService.findById(product.getId()).get().getName());
//        assertEquals("description2", productService.findById(product.getId()).get().getDescription());
//        assertEquals(12, productService.findById(product.getId()).get().getPrice().doubleValue());
//        assertEquals(12, productService.findById(product.getId()).get().getQuantity());
//        assertEquals(ProductStatus.OFFLINE, productService.findById(product.getId()).get().getStatus());
//    }
//
//
//
//    //Tests for method void removeProductQuantity(int requiredQuantity, Long productCode)
//    @Test
//    public void givenZeroForQuantity_WhenRemoveProductQuantity_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.removeProductQuantity(0, product.getProductCode()));
//    }
//    @Test
//    public void givenNegativeForQuantity_WhenRemoveProductQuantity_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.removeProductQuantity(-10, product.getProductCode()));
//    }
//    @Test
//    public void givenNullProductCode_WhenRemoveProductQuantity_ThenThrowInvalidIdValueException(){
//        assertThrows(InvalidIdValueException.class, () ->
//                productService.removeProductQuantity(-10,null));
//    }
//    @Test
//    public void givenNegativeProductCode_WhenRemoveProductQuantity_ThenThrowInvalidIdValueException(){
//
//        assertThrows(InvalidIdValueException.class, () ->
//                productService.removeProductQuantity(10, -10L));
//    }
//    @Test
//    public void givenZeroProductCode_WhenRemoveProductQuantity_ThenThrowInvalidIdValueException(){
//
//        assertThrows(InvalidIdValueException.class, () ->
//                productService.removeProductQuantity(10, 0L));
//    }
//    @Test
//    public void givenProductCodeNotInDb_WhenRemoveProductQuantity_ThenThrowElementNotFoundException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(ElementNotFoundException.class, () ->
//                productService.removeProductQuantity(10, product.getProductCode()+1));
//    }
//    @Test
//    public void givenRequiredQuantityBiggerThanProductQuantity_WhenRemoveProductQuantity_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.removeProductQuantity(
//                        product.getQuantity() + 1, product.getId()));
//    }
//    @Test
//    public void givenRequiredQuantityLessThenProduct_WhenRemoveProductQuantity_ThenProductQuantityMinusRequired(){
//        Product product = productService.saveProduct(testProduct);
//
//        productService.removeProductQuantity(product.getQuantity() - 50, product.getId());
//
//        assertTrue(productService.findById(product.getId()).isPresent());
//        assertEquals(50,
//                productService.findById(product.getId()).get().getQuantity());
//        assertEquals(ProductStatus.ONLINE, productService.findById(product.getId()).get().getStatus());
//
//    }
//    @Test
//    public void givenRequiredQuantityEqualsToProduct_WhenRemoveProductQuantity_ThenProductQuantityZeroAndStatusOFFLINE(){
//        Product product = productService.saveProduct(testProduct);
//        productService.removeProductQuantity(product.getQuantity(), product.getId());
//        assertTrue(productService.findById(product.getId()).isPresent());
//        assertEquals(0, productService.findById(product.getId()).get().getQuantity());
//        assertEquals(ProductStatus.OFFLINE, productService.findById(product.getId()).get().getStatus());
//    }
//
//
//    //Tests for method restoreProductQuantity (Integer quantity, Long productCode)
//    @Test
//    public void givenZeroForQuantity_WhenRestoreProductQuantity_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.addQuantityById( product.getProductCode(), 0));
//    }
//    @Test
//    public void givenNullForQuantity_WhenRestoreProductQuantity_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.addQuantityById( product.getProductCode(), null));
//    }
//    @Test
//    public void givenNegativeForQuantity_WhenRestoreProductQuantity_ThenThrowIllegalArgumentException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(IllegalArgumentException.class, () ->
//                productService.addQuantityById( product.getProductCode(), -10));
//    }
//    @Test
//    public void givenNullProductCode_WhenRestoreProductQuantity_ThenThrowInvalidIdValueException(){
//        assertThrows(InvalidIdValueException.class, () ->
//                productService.addQuantityById(null, -10));
//    }
//    @Test
//    public void givenNegativeProductCode_WhenRestoreProductQuantity_ThenThrowInvalidIdValueException(){
//
//        assertThrows(InvalidIdValueException.class, () ->
//                productService.addQuantityById( -10L, 10));
//    }
//    @Test
//    public void givenZeroProductCode_WhenRestoreProductQuantity_ThenThrowInvalidIdValueException(){
//
//        assertThrows(InvalidIdValueException.class, () ->
//                productService.addQuantityById(0L, 10));
//    }
//    @Test
//    public void givenProductCodeNotInDb_WhenRestoreProductQuantity_ThenThrowElementNotFoundException(){
//        Product product = productService.saveProduct(testProduct);
//        assertThrows(ElementNotFoundException.class, () ->
//                productService.addQuantityById(product.getProductCode()+1, 10));
//    }
//    @Test
//    public void givenQuantity_WhenRestoreProductQuantity_ThenProductQuantityPlusNewQuantity(){
//        Product product = productService.saveProduct(testProduct);
//        productService.addQuantityById( product.getId(), product.getQuantity());
//
//        assertTrue(productService.findById(product.getId()).isPresent());
//        assertEquals(200,
//                productService.findById(product.getId()).get().getQuantity());
//        assertEquals(ProductStatus.ONLINE, productService.findById(product.getId()).get().getStatus());
//
//    }
//    @Test
//    public void givenQuantityAndOFFLINEProduct_WhenRestoreProductQuantity_ThenProductQuantityPlusQuantityAndONLINE(){
//        Product product = productService.saveProduct(testProduct);
//        productService.removeProductQuantity(product.getQuantity(), product.getId());
//        productService.addQuantityById( product.getId(), 50);
//        assertTrue(productService.findById(product.getId()).isPresent());
//        assertEquals(50, productService.findById(product.getId()).get().getQuantity());
//        assertEquals(ProductStatus.ONLINE, productService.findById(product.getId()).get().getStatus());
//    }
//
//    //Tests for method void deleteById(Long id)
//    @Test
//    public void givenNullProductCode_WhenDeleteById_ThenThrowInvalidIdValueException(){
//        assertThrows(InvalidIdValueException.class, () ->productService.deleteById(null));
//    }
//    @Test
//    public void givenNegativeProductCode_WhenDeleteById_ThenThrowInvalidIdValueException(){
//
//        assertThrows(InvalidIdValueException.class, () -> productService.deleteById(-10L));
//    }
//    @Test
//    public void givenZeroProductCode_WhenDeleteById_ThenThrowInvalidIdValueException(){
//
//        assertThrows(InvalidIdValueException.class, () -> productService.deleteById(0L));
//    }
//    @Test
//    public void givenProductCode_WhenDeleteById_ThenRemoveThisRowWithGivenIdInTable(){
//        Product product = productService.saveProduct(testProduct);
//        assertTrue(productService.findById(product.getId()).isPresent());
//        productService.deleteById(product.getId());
//        assertTrue(productService.findById(product.getId()).isEmpty());
//    }
}
