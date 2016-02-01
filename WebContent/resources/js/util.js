/**
 * Create a POST request from a form to the given path
 */
function post(path, method) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    document.body.appendChild(form);
    form.submit();
}