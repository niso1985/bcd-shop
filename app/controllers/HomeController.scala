package controllers

import javax.inject._
import play.api._
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def session() = Action { implicit request: Request[AnyContent] =>
    import com.stripe.Stripe
    import com.stripe.model.checkout.Session
    import java.util
    Stripe.apiKey = "sk_test_IpllZawN1y5zP5fFkG1980MT"

    val params = new util.HashMap[String, AnyRef]

    val paymentMethodTypes = new util.ArrayList[String]
    paymentMethodTypes.add("card")
    params.put("payment_method_types", paymentMethodTypes)

    val lineItems = new util.ArrayList[util.HashMap[String, AnyRef]]
    val lineItem = new util.HashMap[String, AnyRef]
    lineItem.put("name", "T-shirt")
    lineItem.put("description", "Comfortable cotton t-shirt")
    lineItem.put("amount", 500.asInstanceOf[AnyRef])
    lineItem.put("currency", "jpy")
    lineItem.put("quantity", 1.asInstanceOf[AnyRef])
    lineItems.add(lineItem)
    params.put("line_items", lineItems)

    params.put("success_url", "https://example.com/success?session_id={CHECKOUT_SESSION_ID}")
    params.put("cancel_url", "https://example.com/cancel")

    val session = Session.create(params)

    Ok(session.getId)
  }
}
