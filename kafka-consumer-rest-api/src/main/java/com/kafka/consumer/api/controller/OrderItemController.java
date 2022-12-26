package com.kafka.consumer.api.controller;


import com.kafka.consumer.api.model.OrderItem;
import com.kafka.consumer.api.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/orderitem")
public class OrderItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderItemController.class);

    private OrderItemService orderItemService;

    @Autowired
    public void setCustomerService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @Operation(summary = "Create a new order item record")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "201", description = "order item created", content = { @Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "404", description = "Bad request") })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody OrderItem orderItem) {
        OrderItem response = orderItemService.save(orderItem);
        return new ResponseEntity<OrderItem>( response, HttpStatus.OK );
    }

    @Operation(summary = "View a list of order items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping(value = "/list", produces = "application/json")
    public @ResponseBody ResponseEntity<List<OrderItem>> list(){
        List<OrderItem> orderItems = orderItemService.findAll();
        return new ResponseEntity<List<OrderItem>>(orderItems, HttpStatus.OK);
    }

    @Operation(summary = "Delete an order item")
    @DeleteMapping(value="/delete/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        orderItemService.deleteOrderItemByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update an order item")
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<?> updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem orderItem){
        Optional<OrderItem> storedOrderItem = orderItemService.findByID(id);
        storedOrderItem.get().setQuantity(orderItem.getQuantity());
        orderItemService.save(storedOrderItem.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}