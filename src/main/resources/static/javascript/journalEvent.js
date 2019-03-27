//Functions
const signOut = () => {
    $('#signOutForm').submit();
};
const newPost = async () => {
    if(ticketImage != null){
        try{
            let data = await ticketOCR(ticketImage);
            let path = findCityName(data.responses[0].fullTextAnnotation.text);
            path.fromCoordinate = await findCoordinate(path.fromCityName);
            path.toCoordinate = await findCoordinate(path.toCityName);
            addPathInGoogleMap(path);

            console.log(path);

            let req = {
                fromLat: path.fromCoordinate.lat.toString(),
                fromLng: path.fromCoordinate.lng.toString(),
                toLat: path.toCoordinate.lat.toString(),
                toLng: path.toCoordinate.lng.toString()
            };
            $.ajax({
                url: '/path',
                type: 'post',
                contentType:"application/json;charset=UTF-8",
                data: JSON.stringify(req),
                success: () => {
                    $('#postModal').modal('toggle');
                    alert("New post success");
                },
                error: (err) => {
                    console.log(err);
                    alert("New post failed");
                }
            });
        }
        catch (err){
            console.log(err);
            alert("failed");
        }
    }
    ticketImage = null;
    return false;

};
//Events
$('#signOutButton').click(signOut);
$('#newPostButton').click(newPost);
