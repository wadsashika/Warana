/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cse.warana.utility.AggregatedProfileGenerator.Web;

/**
 *
 * @author Anushka
 */
public class ProfileDetailGenerator {

    private String imageURL = null;
    private String name = null;
    private String title = null;
    private String projects = null;
    private String publications = null;
    private String personalDetails = null;

    public void detailFiller() {
        imageURL = "http://4.bp.blogspot.com/-PyLH6GtNHBQ/UH5HkcNQwXI/AAAAAAAAAAU/aur0JqaeAZg/s1600/Cool+short+hairstyles+For+Men+1.jpg";
        name = "Amy Weber";
        title = "com.cse.warana.utility.AggregatedProfileGenerator.Web Desinger";
        projects = "<p>Scenario archetype complementary responsive script pixel sidebar sitemap keep it simple. Complementary visuals footer CSS from alan cooper delightful. Photoshop iconography simplicity user experience affordance narrative ascenders contour. Slab serif interstitial skeuomorphism illustrator design by committee simplicity alan cooper eye tracking. Typography contrast mental model typesetting affordance narrative from CSS. Retina simplicity design by committee typography oblique.</p>\n"
                + "<p>Delightful ascenders contrast prototype. Ligature jakob nielsen user measure. Ligature contrast glyph rule of thirds composition interstitial dribbble. Mental model typography urbanized balance. Resolution rounded corners IDEO constraints dribbble persona. Portfolio sketch baseline 66-character line. Usability testing mental model simplicity aspect ratio pencil type. Usable sans-serif visualization leading prägnanz baseline pencil fireworks clarity omnigraffle.</p>\n"
                + "<div id=\"chartContainer\" style=\"height: 500px; width: 500px;margin: 0 auto\"></div>"
                + "<script> $(function(){\n"
                + "     var chart = new CanvasJS.Chart(\"chartContainer\",\n"
                + "    {\n"
                + "      title:{\n"
                + "        text: \"Gaming Consoles Sold in 2012\"\n"
                + "      },\n"
                + "       data: [\n"
                + "      {\n"
                + "         type: \"pie\",\n"
                + "       dataPoints: [\n"
                + "       {  y: 4181563, legendText:\"PS 3\", indexLabel: \"PlayStation 3\" },\n"
                + "       {  y: 2175498, legendText:\"Wii\", indexLabel: \"Wii\" },\n"
                + "       {  y: 3125844, legendText:\"Xbox\", indexLabel: \"Xbox 360\" },\n"
                + "       {  y: 1176121, legendText:\"DS\" , indexLabel: \"Nintendo DS\"},\n"
                + "       {  y: 1727161, legendText:\"PSP\", indexLabel: \"PSP\" },\n"
                + "       {  y: 4303364, legendText:\"3DS\" , indexLabel: \"Nintendo 3DS\"},\n"
                + "       {  y: 1717786, legendText:\"Vita\" , indexLabel: \"PS Vita\"}\n"
                + "       ]\n"
                + "     }\n"
                + "     ]\n"
                + "   });\n"
                + "\n"
                + "    chart.render();\n"
                + " });</script>";
        publications = "<p>Card-sorting dropdown constraints alan cooper prägnanz iconography. Stakeholder balsamiq italic vector composition ethnography CSS monospace hierarchy. Eye tracking serif persona focus group.</p>\n"
                + "<p>Typesetting artifact rounded corners eye tracking braindump drawer menu mockup dropdown measure. Jony ive eye tracking script monospace heuristic tabs sketches. Affordance eye tracking scenario usable ligature typesetting clarity responsive. Information architecture golden ratio drawer menu header affordance aspect ratio. Balsamiq slab serif IDEO cognitive dissonance kerning pixel pixel-perfect. Pixel rounded corners header workflow portfolio.</p>\n"
                + "<p>Scenario navigation complementary jony ive helvetica archetype interstitial serif front-end. Constraints pencil usability gestalt design language mockup usability testing affordance jakob nielsen.</p>\n";
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getProjects() {
        return projects;
    }

    public String getPublications() {
        return publications;
    }

    public String getPersonalDetails() {
        return personalDetails;
    }
}
