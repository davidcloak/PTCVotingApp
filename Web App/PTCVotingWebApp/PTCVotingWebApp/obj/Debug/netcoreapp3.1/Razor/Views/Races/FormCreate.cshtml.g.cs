#pragma checksum "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "653e91788cc4ca7f321eed4be82be4803aa7d825"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Races_FormCreate), @"mvc.1.0.view", @"/Views/Races/FormCreate.cshtml")]
namespace AspNetCore
{
    #line hidden
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Threading.Tasks;
    using Microsoft.AspNetCore.Mvc;
    using Microsoft.AspNetCore.Mvc.Rendering;
    using Microsoft.AspNetCore.Mvc.ViewFeatures;
#nullable restore
#line 1 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\_ViewImports.cshtml"
using PTCVotingWebApp;

#line default
#line hidden
#nullable disable
#nullable restore
#line 2 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\_ViewImports.cshtml"
using PTCVotingWebApp.Models;

#line default
#line hidden
#nullable disable
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"653e91788cc4ca7f321eed4be82be4803aa7d825", @"/Views/Races/FormCreate.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"c5ea470e98a17a57e2edaf1692c53fd057e8198d", @"/Views/_ViewImports.cshtml")]
    public class Views_Races_FormCreate : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<PTCVotingWebApp.Models.Politcian>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("class", new global::Microsoft.AspNetCore.Html.HtmlString("vertical-center"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("src", new global::Microsoft.AspNetCore.Html.HtmlString("~/images/defaultUser.png"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_2 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("style", new global::Microsoft.AspNetCore.Html.HtmlString("width: 15em; height: 15em;"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_3 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("method", "post", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_4 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("id", new global::Microsoft.AspNetCore.Html.HtmlString("update"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_5 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("id", new global::Microsoft.AspNetCore.Html.HtmlString("poli"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        #line hidden
        #pragma warning disable 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperExecutionContext __tagHelperExecutionContext;
        #pragma warning restore 0649
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner __tagHelperRunner = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperRunner();
        #pragma warning disable 0169
        private string __tagHelperStringValueBuffer;
        #pragma warning restore 0169
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __backed__tagHelperScopeManager = null;
        private global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager __tagHelperScopeManager
        {
            get
            {
                if (__backed__tagHelperScopeManager == null)
                {
                    __backed__tagHelperScopeManager = new global::Microsoft.AspNetCore.Razor.Runtime.TagHelpers.TagHelperScopeManager(StartTagHelperWritingScope, EndTagHelperWritingScope);
                }
                return __backed__tagHelperScopeManager;
            }
        }
        private global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 2 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
  
    var data = ViewBag.poles;

#line default
#line hidden
#nullable disable
            WriteLiteral("<h1>");
#nullable restore
#line 5 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
Write(ViewData["test"]);

#line default
#line hidden
#nullable disable
            WriteLiteral(@"</h1>
<style>
    .conCenter {
        position: fixed;
        z-index: 999;
    }

    div.conCenter {
        background-color: antiquewhite;
        margin: 5px;
        padding: 5px;
        width: 65%;
        height: 75%;
        overflow: auto;
        text-align: justify;
        border: 0.15em solid black;
    }
</style>


<div id=""confermation"" style=""visibility: hidden;"" class=""conCenter"">
");
#nullable restore
#line 26 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
     foreach (var item in ViewBag.pol)
    {


#line default
#line hidden
#nullable disable
            WriteLiteral("        <div class=\"container\" href=\"javascript:{}\"");
            BeginWriteAttribute("onclick", " onclick=\"", 598, "\"", 670, 8);
            WriteAttributeValue("", 608, "send(\'", 608, 6, true);
#nullable restore
#line 29 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
WriteAttributeValue("", 614, item.Name, 614, 10, false);

#line default
#line hidden
#nullable disable
            WriteAttributeValue("", 624, "\',", 624, 2, true);
            WriteAttributeValue(" ", 626, "\'", 627, 2, true);
#nullable restore
#line 29 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
WriteAttributeValue("", 628, item.PoliticalParty, 628, 20, false);

#line default
#line hidden
#nullable disable
            WriteAttributeValue("", 648, "\',\'", 648, 3, true);
#nullable restore
#line 29 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
WriteAttributeValue("", 651, item.Description, 651, 17, false);

#line default
#line hidden
#nullable disable
            WriteAttributeValue("", 668, "\')", 668, 2, true);
            EndWriteAttribute();
            WriteLiteral(" style=\"margin: 1em;\">\n            <div class=\"row\" style=\"border: 0.15em solid black; min-height: 17em;\">\n");
#nullable restore
#line 31 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                 if (item.ImageUrl == null)
                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                    <div class=\"col-3 imageContainer\">\n                        ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("img", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.SelfClosing, "653e91788cc4ca7f321eed4be82be4803aa7d8258506", async() => {
            }
            );
            __Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.Razor.TagHelpers.UrlResolutionTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_Razor_TagHelpers_UrlResolutionTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_0);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_1);
            BeginAddHtmlAttributeValues(__tagHelperExecutionContext, "alt", 1, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
#nullable restore
#line 34 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
AddHtmlAttributeValue("", 984, item.Name, 984, 10, false);

#line default
#line hidden
#nullable disable
            EndAddHtmlAttributeValues(__tagHelperExecutionContext);
            BeginAddHtmlAttributeValues(__tagHelperExecutionContext, "title", 1, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
#nullable restore
#line 34 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
AddHtmlAttributeValue("", 1003, item.Name, 1003, 10, false);

#line default
#line hidden
#nullable disable
            EndAddHtmlAttributeValues(__tagHelperExecutionContext);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_2);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\n                    </div>\n");
#nullable restore
#line 36 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                }
                else
                {

#line default
#line hidden
#nullable disable
            WriteLiteral("                    <div class=\"col-3 imageContainer\">\n                        <img class=\"vertical-center\"");
            BeginWriteAttribute("src", " src=\"", 1244, "\"", 1264, 1);
#nullable restore
#line 40 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
WriteAttributeValue("", 1250, item.ImageUrl, 1250, 14, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            BeginWriteAttribute("alt", " alt=\"", 1265, "\"", 1281, 1);
#nullable restore
#line 40 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
WriteAttributeValue("", 1271, item.Name, 1271, 10, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            BeginWriteAttribute("title", " title=\"", 1282, "\"", 1300, 1);
#nullable restore
#line 40 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
WriteAttributeValue("", 1290, item.Name, 1290, 10, false);

#line default
#line hidden
#nullable disable
            EndWriteAttribute();
            WriteLiteral(" style=\"width: 15em; height: 15em;\" />\n                    </div>\n");
#nullable restore
#line 42 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                }

#line default
#line hidden
#nullable disable
            WriteLiteral("                <div class=\"col-7\">\n                    <h4>");
#nullable restore
#line 44 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                   Write(item.Name);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h4>\n                    <h5>Party: ");
#nullable restore
#line 45 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                          Write(item.PoliticalParty);

#line default
#line hidden
#nullable disable
            WriteLiteral("</h5>\n                    <p>");
#nullable restore
#line 46 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                  Write(item.Description);

#line default
#line hidden
#nullable disable
            WriteLiteral("</p>\n                </div>\n            </div>\n        </div>\n");
#nullable restore
#line 50 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
    }

#line default
#line hidden
#nullable disable
            WriteLiteral("</div>\n\n\n\n\n<div class=\"container\" style=\"margin-bottom: 1em;\">\n    ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "653e91788cc4ca7f321eed4be82be4803aa7d82513689", async() => {
                WriteLiteral("\n        <input id=\"runners\" name=\"runners\"");
                BeginWriteAttribute("value", " value=\"", 1768, "\"", 1776, 0);
                EndWriteAttribute();
                WriteLiteral(" hidden />\n        <input id=\"save\" name=\"save\" hidden />\n\n        <div class=\"row\">\n            <div class=\"col-4\"><label for=\"race\">Race:</label><input name=\"race\"");
                BeginWriteAttribute("value", " value=\"", 1942, "\"", 1950, 0);
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%; height: 2em;\" /></div>\n");
                WriteLiteral("            <div class=\"col-2\">\n                <label for=\"state\">State:</label>\n                <select name=\"state\" style=\"width: 100%; height: 2em;\">\n");
#nullable restore
#line 67 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                     foreach (var state in ViewBag.states)
                    {

#line default
#line hidden
#nullable disable
                WriteLiteral("        ");
                __tagHelperExecutionContext = __tagHelperScopeManager.Begin("option", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "653e91788cc4ca7f321eed4be82be4803aa7d82515031", async() => {
#nullable restore
#line 69 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                          Write(state);

#line default
#line hidden
#nullable disable
                }
                );
                __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.OptionTagHelper>();
                __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper);
                BeginWriteTagHelperAttribute();
#nullable restore
#line 69 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
           WriteLiteral(state);

#line default
#line hidden
#nullable disable
                __tagHelperStringValueBuffer = EndWriteTagHelperAttribute();
                __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value = __tagHelperStringValueBuffer;
                __tagHelperExecutionContext.AddTagHelperAttribute("value", __Microsoft_AspNetCore_Mvc_TagHelpers_OptionTagHelper.Value, global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
                await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
                if (!__tagHelperExecutionContext.Output.IsContentModified)
                {
                    await __tagHelperExecutionContext.SetOutputContentAsync();
                }
                Write(__tagHelperExecutionContext.Output);
                __tagHelperExecutionContext = __tagHelperScopeManager.End();
#nullable restore
#line 69 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\FormCreate.cshtml"
                                              }

#line default
#line hidden
#nullable disable
                WriteLiteral("                </select>\n            </div>\n            <div class=\"col-4\"><label for=\"city\">City:</label><input name=\"city\"");
                BeginWriteAttribute("value", " value=\"", 2530, "\"", 2538, 0);
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%; height: 2em;\" /></div>\n            <div class=\"col-2\"><label for=\"date\">Date:</label><input name=\"date\"");
                BeginWriteAttribute("value", " value=\"", 2663, "\"", 2671, 0);
                EndWriteAttribute();
                WriteLiteral(" type=\"date\" style=\"width: 100%; height: 2em;\" /></div>\n\n        </div>\n    ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string)__tagHelperAttribute_3.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_3);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_4);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\n    ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "653e91788cc4ca7f321eed4be82be4803aa7d82519029", async() => {
                WriteLiteral(@"
        <div style=""border: 0.15em solid black; padding: 1em; margin-top: 1em;"" id=""0Div"">
            <div class=""row"" style=""margin-bottom: 1em;"">
                <div class=""col-6"">
                    <label for=""name"">Name:</label>
                    <input id=""0Name"" name=""name[]""");
                BeginWriteAttribute("value", " value=\"", 3065, "\"", 3073, 0);
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%;\" hidden />\n                    <p id=\"0NameB\"></p>\n                </div>\n                <div class=\"col-6\">\n                    <label for=\"party\">Party:</label>\n                    <input id=\"0Party\" name=\"party[]\"");
                BeginWriteAttribute("value", " value=\"", 3312, "\"", 3320, 0);
                EndWriteAttribute();
                WriteLiteral(@" style=""width: 100%;"" hidden />
                    <p id=""0PartyB""></p>
                </div>
            </div>
            <div class=""row"">
                <div class=""col-12"">
                    <label for=""description"">Description:</label>
                    <textarea id=""0Des"" name=""description[]"" rows=""8"" style=""width: 100%;"" hidden></textarea>
                    <p id=""0DesB"" style=""min-height: 8em;""></p>

                </div>
            </div>
            <a href=""javascript:{}"" onclick=""picker('0');"">Pick New Politician</a>
            <a href=""javascript:{}"" onclick=""remove('0Div');"">Remove Politician</a>
        </div>

    ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_5);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\n</div>\n\n<a href=\"javascript:{}\" onclick=\"AddRunner();\">Add Runner</a>\n<a href=\"javascript:{}\" onclick=\"buildRunners();\">Save</a>\n<a href=\"javascript:{}\" onclick=\"cancel();\">Cancel</a>\n\n<div style=\"height: 3em;\"></div>");
        }
        #pragma warning restore 1998
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.ViewFeatures.IModelExpressionProvider ModelExpressionProvider { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IUrlHelper Url { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.IViewComponentHelper Component { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IJsonHelper Json { get; private set; }
        [global::Microsoft.AspNetCore.Mvc.Razor.Internal.RazorInjectAttribute]
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<PTCVotingWebApp.Models.Politcian> Html { get; private set; }
    }
}
#pragma warning restore 1591
