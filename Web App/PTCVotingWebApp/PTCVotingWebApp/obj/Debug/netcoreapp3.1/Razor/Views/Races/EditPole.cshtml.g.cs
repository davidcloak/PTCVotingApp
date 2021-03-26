#pragma checksum "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "5284cfd97914b6cb751a9000141e50fcd9d901e6"
// <auto-generated/>
#pragma warning disable 1591
[assembly: global::Microsoft.AspNetCore.Razor.Hosting.RazorCompiledItemAttribute(typeof(AspNetCore.Views_Races_EditPole), @"mvc.1.0.view", @"/Views/Races/EditPole.cshtml")]
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
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"5284cfd97914b6cb751a9000141e50fcd9d901e6", @"/Views/Races/EditPole.cshtml")]
    [global::Microsoft.AspNetCore.Razor.Hosting.RazorSourceChecksumAttribute(@"SHA1", @"ef765232e14474621993b1092cdf6b7a6743052b", @"/Views/_ViewImports.cshtml")]
    public class Views_Races_EditPole : global::Microsoft.AspNetCore.Mvc.Razor.RazorPage<dynamic>
    {
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_0 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("method", "post", global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
        private static readonly global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute __tagHelperAttribute_1 = new global::Microsoft.AspNetCore.Razor.TagHelpers.TagHelperAttribute("id", new global::Microsoft.AspNetCore.Html.HtmlString("update"), global::Microsoft.AspNetCore.Razor.TagHelpers.HtmlAttributeValueStyle.DoubleQuotes);
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
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper;
        private global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper;
        #pragma warning disable 1998
        public async override global::System.Threading.Tasks.Task ExecuteAsync()
        {
#nullable restore
#line 4 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
  
    var data = ViewBag.poles;

#line default
#line hidden
#nullable disable
            WriteLiteral(@"
<script>
    function buildRunners() {
        var runners = """";
        var names = document.getElementsByName(""name[]"");
        var partys = document.getElementsByName(""party[]"");
        var descriptions = document.getElementsByName(""description[]"");

        for (var i = 0; i < names.length; i++) {
            if (descriptions[i].value == """") {
                runners += names[i].value + ""~"" + partys[i].value + ""~NoDesc;"";
            } else {
                runners += names[i].value + ""~"" + partys[i].value + ""~"" + descriptions[i].value + "";"";
            }
        }
        alert(runners);
    }
</script>

");
#nullable restore
#line 26 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
 for (int i = 0; i < data.Length; i++)
{
    var runners = data[i].Runners.ToArray();


#line default
#line hidden
#nullable disable
            WriteLiteral("    <div class=\"container\" style=\"margin-bottom: 1em;\">\r\n        ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "5284cfd97914b6cb751a9000141e50fcd9d901e65160", async() => {
                WriteLiteral("\r\n            <input id=\"runners\" name=\"runners\"");
                BeginWriteAttribute("value", " value=\"", 1038, "\"", 1046, 0);
                EndWriteAttribute();
                WriteLiteral("/>\r\n            <div class=\"row\">\r\n                <div class=\"col-4\"><label for=\"race\">Race:</label><input name=\"race\"");
                BeginWriteAttribute("value", " value=\"", 1166, "\"", 1187, 1);
#nullable restore
#line 34 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 1174, data[i].Race, 1174, 13, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%;\" /></div>\r\n                <div class=\"col-4\"><label for=\"state\">State:</label><input name=\"state\"");
                BeginWriteAttribute("value", " value=\"", 1307, "\"", 1329, 1);
#nullable restore
#line 35 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 1315, data[i].State, 1315, 14, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%;\" /></div>\r\n                <div class=\"col-4\"><label for=\"city\">City:</label><input name=\"city\"");
                BeginWriteAttribute("value", " value=\"", 1446, "\"", 1467, 1);
#nullable restore
#line 36 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 1454, data[i].City, 1454, 13, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%;\" /></div>\r\n            </div>\r\n        ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper.Method = (string)__tagHelperAttribute_0.Value;
            __tagHelperExecutionContext.AddTagHelperAttribute(__tagHelperAttribute_0);
            __tagHelperExecutionContext.AddHtmlAttribute(__tagHelperAttribute_1);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n        ");
            __tagHelperExecutionContext = __tagHelperScopeManager.Begin("form", global::Microsoft.AspNetCore.Razor.TagHelpers.TagMode.StartTagAndEndTag, "5284cfd97914b6cb751a9000141e50fcd9d901e68521", async() => {
                WriteLiteral("\r\n");
#nullable restore
#line 40 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
             for (int x = 0; x < runners.Length; x++)
            {

#line default
#line hidden
#nullable disable
                WriteLiteral(@"                <div style=""border: 0.15em solid black; padding: 1em; margin-top: 1em;"">
                    <div class=""row"" style=""margin-bottom: 1em;"">
                        <div class=""col-6"">
                            <label for=""name"">Name:</label>
                            <input");
                BeginWriteAttribute("id", " id=\"", 1920, "\"", 1936, 1);
#nullable restore
#line 46 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 1925, "name"+x, 1925, 11, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" name=\"name[]\"");
                BeginWriteAttribute("value", " value=\"", 1951, "\"", 1975, 1);
#nullable restore
#line 46 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 1959, runners[x].Name, 1959, 16, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" style=\"width: 100%;\" />\r\n                        </div>\r\n                        <div class=\"col-6\">\r\n                            <label for=\"party\">Party:</label>\r\n                            <input");
                BeginWriteAttribute("id", " id=\"", 2176, "\"", 2193, 1);
#nullable restore
#line 50 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 2181, "party"+x, 2181, 12, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" name=\"party[]\"");
                BeginWriteAttribute("value", " value=\"", 2209, "\"", 2243, 1);
#nullable restore
#line 50 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 2217, runners[x].PoliticalParty, 2217, 26, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(@" style=""width: 100%;"" />
                        </div>
                    </div>
                    <div class=""row"">
                        <div class=""col-12"">
                            <label for=""description"">Description:</label>
                            <textarea");
                BeginWriteAttribute("id", " id=\"", 2527, "\"", 2550, 1);
#nullable restore
#line 56 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
WriteAttributeValue("", 2532, "description"+x, 2532, 18, false);

#line default
#line hidden
#nullable disable
                EndWriteAttribute();
                WriteLiteral(" name=\"description[]\" rows=\"8\" style=\"width: 100%;\">");
#nullable restore
#line 56 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
                                                                                                            Write(runners[x].Description);

#line default
#line hidden
#nullable disable
                WriteLiteral("</textarea>\r\n                        </div>\r\n                    </div>\r\n                </div>\r\n");
#nullable restore
#line 60 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
            }

#line default
#line hidden
#nullable disable
                WriteLiteral("\r\n        ");
            }
            );
            __Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.FormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_FormTagHelper);
            __Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper = CreateTagHelper<global::Microsoft.AspNetCore.Mvc.TagHelpers.RenderAtEndOfFormTagHelper>();
            __tagHelperExecutionContext.Add(__Microsoft_AspNetCore_Mvc_TagHelpers_RenderAtEndOfFormTagHelper);
            await __tagHelperRunner.RunAsync(__tagHelperExecutionContext);
            if (!__tagHelperExecutionContext.Output.IsContentModified)
            {
                await __tagHelperExecutionContext.SetOutputContentAsync();
            }
            Write(__tagHelperExecutionContext.Output);
            __tagHelperExecutionContext = __tagHelperScopeManager.End();
            WriteLiteral("\r\n    </div>\r\n    <a href=\"javascript:{}\" onclick=\"buildRunners();\">Save</a>\r\n");
#nullable restore
#line 65 "C:\!Enterprise\PTCVotingApp\Web App\PTCVotingWebApp\PTCVotingWebApp\Views\Races\EditPole.cshtml"
}

#line default
#line hidden
#nullable disable
            WriteLiteral("<div style=\"height: 3em;\"></div>");
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
        public global::Microsoft.AspNetCore.Mvc.Rendering.IHtmlHelper<dynamic> Html { get; private set; }
    }
}
#pragma warning restore 1591