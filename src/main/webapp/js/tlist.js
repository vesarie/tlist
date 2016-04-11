// Custom JavaScript for this application

$.datepicker.setDefaults({
    firstDay: 1,
    dateFormat: "yy-mm-dd"
});

// Initializes the "Edit task" dialog
$('#editTaskModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('taskId');
    var schedule = button.data('taskSchedule');
    var priority = button.data('taskPriority');

    var name = $('#task' + id + '-name').text();

    $('#editTask-id').val(id);
    $('#editTask-name').val(name);
    $('#editTask-schedule').val(schedule);
    $('#editTask-priority-' + priority).attr('selected', 'selected');
    
    $('#editTask-schedule').datepicker();
});

//$('.modal').on('show.bs.modal', 'form[data-async]', function (event) {
//    var button = $(event.relatedTarget);
//    var id = button.data('taskId');
//    var $form = $(this);
//    var $idInput = $($form.attr('data-id-input'));
//    var $target = $($form.attr('data-target'));
//    
//    $idInput.val(id);
//
//    $.ajax({
//        type: $form.attr('method'),
//        url: $form.attr('action'),
//        data: $form.serialize(),
//        
//        success: function (data, status) {
//            $target.html(data);
//        }
//    });
//});

$('.modal').on('submit', 'form[data-async]', function (event) {
    var $form = $(this);
    var $target = $($form.attr('data-target'));

    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize(),
        success: function (data, status) {
            $target.html(data);
        }
    });

    event.preventDefault();
});