package br.com.audsat.vehicleinsurancews.controller;

import br.com.audsat.vehicleinsurancews.dto.BudgetDtoIn;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Budget Controller", description = "Endpoints para orçamentos")
@RestController
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @Operation(summary = "Cadastrar orçamento")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Budget created successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDtoOut.class))})})
    @PostMapping
    public ResponseEntity<BudgetDtoOut> createBudget(@Valid @RequestBody BudgetDtoIn dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(dto));
    }

    @Operation(summary = "Consultar orçamento ")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Budget found successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDtoOut.class))})})
    @GetMapping("/{insuranceId}")
    public ResponseEntity<BudgetDtoOut> findBudgetByInsuranceId(@PathVariable Long insuranceId) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.findBudgetByInsuranceId(insuranceId));
    }

    @Operation(summary = "Atualizar orçamento")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Budget updated successfully",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDtoOut.class))})})
    @PutMapping("/{insuranceId}")
    public ResponseEntity<BudgetDtoOut> updateBudget(@PathVariable Long insuranceId,
                                                     @Valid @RequestBody BudgetDtoIn dto) {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.updateBudget(insuranceId, dto));
    }

    @Operation(summary = "Remover orçamento")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Budget removed successfully")})
    @DeleteMapping("/{insuranceId}")
    public ResponseEntity<?> deleteBudgetByInsuranceId(@PathVariable Long insuranceId) {
        budgetService.deleteBudgetByInsuranceId(insuranceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
