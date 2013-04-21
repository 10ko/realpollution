<html>
<head>
  <title>Welcome User</title>
  <meta name="description" content="" />
  <meta name="keywords" content="" />
  <meta name="author" content="" />
  <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
  <link rel="stylesheet" type="text/css" href="/static/login.css" />

  <script type="text/javascript" src="//use.typekit.net/xbu2lnn.js"></script>
  <script type="text/javascript">try{Typekit.load();}catch(e){}</script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=visualization"></script>
  <script src="/static/js/highcharts.js"></script>
  <script src="/static/js/modules/exporting.js"></script>
  <script type="text/javascript">
    function startTime(){
      var today=new Date();
      var days = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat'];
      var months = ['January','February','March','April','May','June','July','August','September','October','November','December'];
      var h = checkTime(today.getHours());
      var m=checkTime(today.getMinutes());
      var s=checkTime(today.getSeconds());
      var day = days[ today.getDay() ];
      var month = months[ today.getMonth() ];
      $('#clock-content').html(h+":"+m+":"+s);
      $('#cal-content').html(day+", "+today.getDate()+"   "+month+"   2013");
      t=setTimeout(function(){startTime()},500);
    }

    function checkTime(i){
      if (i<10)
        i="0" + i;
      return i;
    }



$(function () {
        $('#chart-elem').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Your pollution information from last week'
            },
            xAxis: {
                categories: ['Today', 'Saturday', 'Friday', 'Thursday', 'Wednesday', 'Tuesday', 'Monday'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Pollution (units)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' Units'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -100,
                y: 30,
                floating: true,
                borderWidth: 1,
                backgroundColor: '#FFFFFF',
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'Lowest value',
                data: [107, 31, 635, 203, 2, 45, 184]
            }, {
                name: 'Average value',
                data: [133, 156, 947, 408, 6, 40, 178]
            }, {
                name: 'Highest value',
                data: [973, 914, 1500, 732, 34, 39, 180]
            }]
        });
    });


    $


    function googleMapInit(jsonPoints){
        var map, pointarray, heatmap;

        var taxiData = new Array();

         for(var i in jsonPoints){
             var elem = jsonPoints[i];
            for(var e in elem)
                taxiData[e] = new google.maps.LatLng(elem[e].longCord, elem[e].latCord);
         }

      function initialize() {
        var mapOptions = {
          zoom: 16,
          center: new google.maps.LatLng(51.52403,-0.086619),
          mapTypeId: google.maps.MapTypeId.SATELLITE
        };

        map = new google.maps.Map(document.getElementById('map-canvas'),
            mapOptions);

        var pointArray = new google.maps.MVCArray(taxiData);

        heatmap = new google.maps.visualization.HeatmapLayer({
          data: pointArray
        });

        heatmap.setMap(map);
      }

      function toggleHeatmap() {
        heatmap.setMap(heatmap.getMap() ? null : map);
      }

      function changeGradient() {
        var gradient = [
          'rgba(0, 255, 255, 0)',
          'rgba(0, 255, 255, 1)',
          'rgba(0, 191, 255, 1)',
          'rgba(0, 127, 255, 1)',
          'rgba(0, 63, 255, 1)',
          'rgba(0, 0, 255, 1)',
          'rgba(0, 0, 223, 1)',
          'rgba(0, 0, 191, 1)',
          'rgba(0, 0, 159, 1)',
          'rgba(0, 0, 127, 1)',
          'rgba(63, 0, 91, 1)',
          'rgba(127, 0, 63, 1)',
          'rgba(191, 0, 31, 1)',
          'rgba(255, 0, 0, 1)'
        ]
        heatmap.setOptions({
          gradient: heatmap.get('gradient') ? null : gradient
        });
      }

      function changeRadius() {
        heatmap.setOptions({radius: heatmap.get('radius') ? null : 20});
      }

      function changeOpacity() {
        heatmap.setOptions({opacity: heatmap.get('opacity') ? null : 0.2});
      }

      //google.maps.event.addDomListener(window, 'load', initialize);
      initialize();
  }


  $.getJSON('http://realpollution.appspot.com/api/data/get/james',function(data){
        googleMapInit(data);
  });

  startTime();


  
  </script>


</head>
<body>
    <div id="content">
      <section id="header">
        <p>realPollution: a <a href="http://spaceappschallenge.org" target="_blank">SpaceApps Challenge</a> demo.</p>
          </section>
      <section id="logo">
        <span>Dashboard</span><p>realPollution.</p>
      </section>
      <article>
        <section id="greetings">
          <section id="avatar"></section>
          <section id="hello-text">
            <h1>Hello,<br><span>Wiz-Cat</span></h1>
            <p>Where are you going today?</p>
          </section>
          <section id="clock">
            <p id="clock-content"></p>
            <p id="cal-content"></p>
          </section>
        </section>
        <div id="button-container">
          <a href="#"><div class="bleft but"><p>Mon</p></div></a>
          <a href="#"><div class="bcent but"><p>Tue</p></div></a>
          <a href="#"><div class="bcent but"><p>Wed</p></div></a>
          <a href="#"><div class="bcent but"><p>Thu</p></div></a>
          <a href="#"><div class="bcent but"><p>Fri</p></div></a>
          <a href="#"><div class="bcent but"><p>Sat</p></div></a>
          <a href="#"><div class="bright but"><p>Today</p></div></a>
        </div>
        <div id="map-canvas"></div>
        <div id="chart-head">
          <h1>Your week in chart:</h1>
        </div>
        <div id="chart-elem"></div>
      </article>
      <footer><p>Designed and developed by <a href="https://github.com/J3tto">J3tto</a> & <a href="http://www.twitter.com/iamtenko" target="_blank">@iamtenko</a></p></footer>
    </div>
</body>
</html>