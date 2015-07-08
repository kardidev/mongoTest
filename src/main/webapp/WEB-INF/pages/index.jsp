<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>Mongo demo</title>
  <script src="resources/js/jquery-2.1.4.min.js"></script>
</head>
<body>
  <h2>Create a person:</h2>
  <form action="/" id="creationForm">
    <label for="name">Name</label>
    <input id="name" type="text" name="name" value="Nick" />
    <br/>
    <label for="age">Age</label>
    <input id="age" type="text" name="age" value="33"/>
    <br/>
    <label for="profession">Profession</label>
    <input id="profession" type="text" name="profession" value="programmer"/>
    <br/>
    <input type="submit" value="Create">
  </form>

  <br/>
  <h2>Get the collection:</h2>

  <div id="result"></div>


  <script>

    // Attach a submit handler to the form
    $( "#creationForm" ).submit(function( event ) {

      // Stop form from submitting normally
      event.preventDefault();

      $.ajax({
        url: "mongo/insert",
        type: 'POST',
        dataType: 'json',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        data: JSON.stringify({
          id: "1",
          name: $("#name").val(),
          age: $("#age").val(),
          profession: $("#profession").val()
        }),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function(data) {
          if (data.result) {
            LoadAllPersons();
          } else {
            alert("error:" + data.message);
          }
        },
        error:function(data,status,er) {
          alert("error: "+data+" status: "+status+" er:"+er);
        }
      });
    });

    // Run automatically
    LoadAllPersons();
    function LoadAllPersons() {
      $.ajax({
        url: "mongo/getall",
        type: 'GET',
        headers: {
          'Accept': 'application/json'
        },
        success: function(data) {
          if (data.result) {
            populatePersons(data.persons);
          } else {
            alert("error:" + data.message);
          }
        },
        error:function(data,status,er) {
          alert("error: "+data+" status: "+status+" er:"+er);
        }
      });
    }

    function populatePersons(persons) {
      $("#result").empty();
      persons.forEach(function(entry) {
        $("#result").append("["+entry.id+"] "+entry.name+" "+entry.age+" "+entry.profession+" <br/>");
      });
    }

  </script>

</body>
</html>
