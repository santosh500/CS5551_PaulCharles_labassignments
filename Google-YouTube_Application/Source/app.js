//****THIS CODE IS TAKEN AND REFERENCED FROM: https://github.com/FriesFlorian/ViralVideos
//****THIS CODE IS TAKEN AND REFERENCED FROM: https://www.youtube.com/watch?v=-vH2eZAM30s

function tplawesome(e,t)
{
    res=e;
    for(var n=0;n<t.length;n++)
    {
        res=res.replace
    (/\{\{(.*?)\}\}/g,function(e,r)
    {
        return t[n][r]})
    }
    return res
}

$(function() {
    $("form").on("submit", function(e) {
       e.preventDefault();

       var request = gapi.client.youtube.search.list({
            part: "snippet",
            type: "video",
            q: encodeURIComponent($("#word").val()).replace(/%20/g, "+"),
           //show one result
            maxResults: 1,
           //based on viewcount
            order: "viewCount",
            publishedAfter: "2007-01-01T00:00:00Z"
       }); 

       request.execute(function(response) {
          var results = response.result;
          $("#results").html("");
          $.each(results.items, function(index, item) {
            $.get("item.html", function(data) {
                $("#results").append(tplawesome(data, [{"title":item.snippet.title, "videoid":item.id.videoId}]));
            });
          });
          resetVideoHeight();
       });
    });
    
    $(window).on("resize", resetVideoHeight);
});

function resetVideoHeight()
{
    $(".video").css("height", $("#results").width() * 9/16);
}

function init() {
    gapi.client.setApiKey("AIzaSyAI_byjzhHQ3cXcpe70aXDjNVNgl1CCe-k");
    gapi.client.load("youtube", "v3", function() {

    });
}
