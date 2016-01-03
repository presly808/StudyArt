package servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Razer on 02.01.16.
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("\n" +
                "<!doctype html><head><style type='text/css'>body {font-size: small; font-family: verdana, arial, helvetica, sans-serif;}pre, code {font-size:medium;}a { text-decoration:none; }h1,.h1 { font-size:300%; }h2,.h2 { font-size:150%; }h3 { font-size:120%; }textarea[disabled]{background-color: #F8F8F8; }</style><title>StudyART </title></head><body>\n" +
                "<div style='float:right;  margin:0px; border: 1px solid lightgray;'><table><form  method=post action=/login><tr><td>id/email</td><td><input type=text name=uname size=20></td></tr><tr><td>password</td><td><input type=password name=pw size=20></td></tr><tr><td></td><td><input type=submit name=dologin value='log in'></td></tr> <input type=hidden name=fromurl value='/'></form><tr><td></td><td><a href=/reset>forgot password</a></td></tr><tr><td></td><td><a href='/pref?docreate=1'>create account</a></td></tr><tr><td colspan=2>&nbsp;<hr></td></tr><tr><td colspan=2>Or use <a href=https://login.persona.org/about>Mozilla Persona</a>:</td></tr><tr><td colspan=2><p style='max-width:250px;'>Mozilla Persona lets you create an account using your gmail or yahoo login, avoiding a separate Codingbat password.</p></td></tr><tr><td></td><td><input type='image' onclick=\"logButton('2', 'btn-log-in'); navigator.id.get(personaCallback);\" src='/btn-log-in.png'><br><input type='image' onclick=\"logButton('2','btn-create'); navigator.id.get(personaCallback);\"  src='/btn-create.png'></td></tr></table></div><script type=text/javascript>document.pconfig=2;</script><div  style='float:right'><table><tr><td valign=top style='text-align:right' colspan=1><a href=/about.html>about</a> | <a href=/help.html>help</a> | <a href=/doc/code-help-videos.html>code help+videos | <a href=/done>done</a> | <a href='/pref'>prefs</a> </td></tr></table></div><a href=/><span style='font-size:200%;'>StudyART" +
                "</span></a> code practice<p style='max-width:800px'>Online code practice (<a href='/prob/p151833'>example problem</a>).  <a href=/doc/practice/code-badges.html><b>Code Badges</b></a><br><b>New videos</b>:<a href=/doc/practice/mod-introduction.html>Introduction to Mod</a> | <a href=/doc/practice/makebricks-introduction.html>MakeBricks problem and solution</a> | <a href=/doc/practice/fizzbuzz-code.html>FizzBuzz the famous</a> code interview question.<br><a href=help.html#teacher>Teacher Features</a>, <a href=/progress-graphs.html>Progress Graphs</a>, <a href=/done/epic>Epic Progress Graphs</a>, author your own <a href=/authoring.html>pages and problems</a>, <a href=http://codingbat.blogspot.com>codingbat blog</a>. Please <a href=mailto:nick.parlante@cs.stanford.edu?subject=codingbat>Email Nick</a> with any comments or suggestions. Practice works.<table><tr><td valign=top><a href=/java><span class=h2><b>Java</b></span></a>&nbsp;&nbsp;<a href=/java>All Java Sections + help/videos</a><br><table cellpadding=10><tr><td><a href='/java/Warmup-1'><span class=h2>Warmup-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Simple warmup problems to get started (solutions available)<br><nobr><img src=/c1.jpg><a href='/prob/p187868'>sleepIn</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p181646'>monkeyTrouble</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p154485'>sumDouble</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; &nbsp; <a href='/java/Warmup-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/java/Warmup-2'><span class=h2>Warmup-2</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Medium warmup string/array loops  (solutions available)<br><nobr><img src=/c1.jpg><a href='/prob/p142270'>stringTimes</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p101475'>frontTimes</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p194667'>countXX</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; &nbsp; <a href='/java/Warmup-2'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/java/String-1'><span class=h2>String-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Basic string problems -- no loops<br><nobr><img src=/c1.jpg><a href='/prob/p171896'>helloName</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p161056'>makeAbba</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p147483'>makeTags</a></nobr>&nbsp; &nbsp; <a href='/java/String-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/java/Array-1'><span class=h2>Array-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Basic array problems -- no loops.<br><nobr><img src=/c1.jpg><a href='/prob/p185685'>firstLast6</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p118976'>sameFirstLast</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p167011'>makePi</a></nobr>&nbsp; &nbsp; <a href='/java/Array-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/java/Logic-1'><span class=h2>Logic-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Basic boolean logic puzzles -- if else && || !<br><nobr><img src=/c1.jpg><a href='/prob/p159531'>cigarParty</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p103360'>dateFashion</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p141061'>squirrelPlay</a></nobr>&nbsp; &nbsp; <a href='/java/Logic-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/java/Logic-2'><span class=h2>Logic-2</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Medium boolean logic puzzles -- if else && || !<br><nobr><img src=/c1.jpg><a href='/prob/p183562'>makeBricks</a></nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p148972'>loneSum</a></nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p130788'>luckySum</a></nobr>&nbsp; &nbsp; <a href='/java/Logic-2'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "</table>\n" +
                "&nbsp;&nbsp;...<a href=/java>All Java Sections + help/videos</a></td>\n" +
                "<td width='20%'>&nbsp;</td><td valign=top><a href=/python><span class=h2><b>Python</b></span></a>&nbsp;&nbsp;<a href=/python>All Python Sections + help</a><br><table cellpadding=10><tr><td><a href='/python/Warmup-1'><span class=h2>Warmup-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Simple warmup problems to get started, no loops (solutions available)<br><nobr><img src=/c1.jpg><a href='/prob/p173401'>sleep_in</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p120546'>monkey_trouble</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p141905'>sum_double</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; &nbsp; <a href='/python/Warmup-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/python/Warmup-2'><span class=h2>Warmup-2</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Medium warmup string/list problems with loops  (solutions available)<br><nobr><img src=/c1.jpg><a href='/prob/p193507'>string_times</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p165097'>front_times</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p113152'>string_bits</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; &nbsp; <a href='/python/Warmup-2'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/python/String-1'><span class=h2>String-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Basic python string problems -- no loops<br><nobr><img src=/c1.jpg><a href='/prob/p115413'>hello_name</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p182144'>make_abba</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p132290'>make_tags</a></nobr>&nbsp; &nbsp; <a href='/python/String-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/python/List-1'><span class=h2>List-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Basic python list problems -- no loops.<br><nobr><img src=/c1.jpg><a href='/prob/p181624'>first_last6</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p179078'>same_first_last</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p113659'>make_pi</a></nobr>&nbsp; &nbsp; <a href='/python/List-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/python/Logic-1'><span class=h2>Logic-1</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Basic boolean logic puzzles -- if else and or not<br><nobr><img src=/c1.jpg><a href='/prob/p195669'>cigar_party</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p129125'>date_fashion</a>&nbsp;<font color=gray>H</font> </nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p135815'>squirrel_play</a></nobr>&nbsp; &nbsp; <a href='/python/Logic-1'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "<tr><td><a href='/python/Logic-2'><span class=h2>Logic-2</span></a> <img src=/s1.jpg><img src=/s1.jpg><img src=/s1.jpg><br>Medium boolean logic puzzles -- if else and or not<br><nobr><img src=/c1.jpg><a href='/prob/p118406'>make_bricks</a></nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p143951'>lone_sum</a></nobr>&nbsp; <nobr><img src=/c1.jpg><a href='/prob/p107863'>lucky_sum</a></nobr>&nbsp; &nbsp; <a href='/python/Logic-2'>more</a><br><img src=/c1.jpg></td></tr>\n" +
                "</table>\n" +
                "&nbsp;&nbsp;...<a href=/python>All Python Sections + help</a></td></tr></table><p style='font-size:x-small;'>Copyright <a style='font-size:x-small;' href='http://www-cs-faculty.stanford.edu/~nick'>Nick Parlante</a> 2006-11 - <a style='font-size:x-small;' href=/privacy.html>privacy</a><script src='https://login.persona.org/include.js'></script>\n" +
                "<script type=text/javascript>\n" +
                "// http://stackoverflow.com/questions/133925/javascript-post-request-like-a-form-submit\n" +
                "// stackoverflow:\n" +
                "function post_to_url(path, params, method) {\n" +
                "    method = method || \"post\"; // Set method to post by default if not specified.\n" +
                "\n" +
                "    // The rest of this code assumes you are not using a library.\n" +
                "    // It can be made less wordy if you use one.\n" +
                "    var form = document.createElement(\"form\");\n" +
                "    form.setAttribute(\"method\", method);\n" +
                "    form.setAttribute(\"action\", path);\n" +
                "\n" +
                "    for(var key in params) {\n" +
                "        if(params.hasOwnProperty(key)) {\n" +
                "            var hiddenField = document.createElement(\"input\");\n" +
                "            hiddenField.setAttribute(\"type\", \"hidden\");\n" +
                "            hiddenField.setAttribute(\"name\", key);\n" +
                "            hiddenField.setAttribute(\"value\", params[key]);\n" +
                "\n" +
                "            form.appendChild(hiddenField);\n" +
                "         }\n" +
                "    }\n" +
                "\n" +
                "    document.body.appendChild(form);\n" +
                "    form.submit();\n" +
                "}\n" +
                "\n" +
                "// Called by persona checker, post to our server\n" +
                "function personaCallback(assertion) {\n" +
                "  post_to_url(\"/plogin\", {\"assertion\": assertion, \"pconfig\":document.pconfig});\n" +
                "  // Want to post in a way that, upon success, refreshes the page, below does not\n" +
                "  //var req = new XMLHttpRequest();\n" +
                "  //req.open(\"POST\", '/plogin', true);\n" +
                "  //req.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
                "  //req.send('pconfig=' + document.pconfig + '&assertion=' + assertion);\n" +
                "}\n" +
                "\n" +
                "\n" +
                "// Minimal POST of some data, no response.\n" +
                "function logButton(pconfig, button_name){\n" +
                "  var req = new XMLHttpRequest();\n" +
                "  req.open(\"POST\", '/plogin', true);\n" +
                "  req.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
                "  req.send('pconfig=' + pconfig + '&button=' + button_name);\n" +
                "}\n" +
                "\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n");
        pw.flush();
    }
}
