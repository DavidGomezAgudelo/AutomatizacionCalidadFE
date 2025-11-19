package org.calidadsoftware.stepdefinitions;

import org.calidadsoftware.questions.ProductDetailsVisible;
import org.calidadsoftware.tasks.ViewProductDetail;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

// definiciones de pasos para ver el detalle de un producto
public class VerProductDetailStepDefinitions {

    @When("selecciona un producto")
    public void selecciona_producto() {
        CommonStepDefinitions.actor.attemptsTo(ViewProductDetail.ofFirstProduct());
    }

    @Then("visualiza toda la información detallada del producto")
    public void visualiza_informacion_detallada() {
        CommonStepDefinitions.actor.should(seeThat(ProductDetailsVisible.are(), is(true)));
    }
}
