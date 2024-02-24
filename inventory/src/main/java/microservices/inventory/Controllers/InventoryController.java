package microservices.inventory.Controllers;

import microservices.inventory.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/isInventoryInStock/{sku-code}")
    public ResponseEntity<Object> isInStock(@PathVariable("sku-code") String skuCode) {
        try {
            return new ResponseEntity<>(inventoryService.isInStock(skuCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
