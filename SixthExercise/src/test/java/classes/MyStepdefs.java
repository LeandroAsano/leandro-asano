import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MyStepdefs {

    @Given("^all my posts$")
    public void allMyPosts() throws Throwable {
    }

    @When("^i run that app$")
    public void iRunThatApp() throws Throwable {
    }

    @Then("^show me (\\d+) lasts posts$")
    public void showMeLastsPosts(int arg0) throws Throwable {
    }


    @When("^i select \"([^\"]*)\" option$")
    public void iSelectOption(String arg0) throws Throwable {
    }

    @Then("^i see my most liked posts$")
    public void iSeeMyMostLikedPosts() throws Throwable {
    }

    @Given("^all the blogger posts$")
    public void allTheBloggerPosts() throws Throwable {
    }

    @When("^i select select a blogger post$")
    public void iSelectSelectABloggerPost() throws Throwable {
    }

    @Then("^i be subscribe to an blogger posts$")
    public void iBeSubscribeToAnBloggerPosts() throws Throwable {
    }
}
