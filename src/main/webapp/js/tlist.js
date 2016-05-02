// Custom JavaScript for this application

$.datepicker.setDefaults({
    firstDay: 1,
    dateFormat: "yy-mm-dd"
});

$(document).ready(function ($) {
    $('.btn-submit-on-click').on('click', function () {
        $($(this).data('form')).submit();
    });
});

// Initializes the "Create task" dialog
$('#createTaskModal').on('show.bs.modal', function (event) {
    $('#createTask-name').val('');
    $('#createTask-schedule').val('');
    $('#createTask-priority').val(4);

    $(this).find('div.has-error').removeClass('has-error');
    $(this).find('span.help-block').text('');
    $(this).find('input[data-datepicker]').datepicker();
});

// Initializes the "Edit task" dialog
$('#editTaskModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('taskId');
    var priority = button.data('taskPriority');
    var schedule = button.data('taskSchedule');
    var name = $('#task' + id + '-name').text();

    $('#editTask').data('saved', false);
    $('#editTask-id').val(id);
    $('#deleteTask-id').val(id);
    $('#editTask-name').val(name);
    $('#editTask-priority').val(priority);
    $('#editTask-schedule').val(schedule);

    $(this).find('div.has-error').removeClass('has-error');
    $(this).find('span.help-block').text('');
    $(this).find('input[data-datepicker]').datepicker();
});

// Initializes the "Create project" dialog
$('#createProjectModal').on('show.bs.modal', function (event) {
    $('#createProject-name').val('');

    $(this).find('div.has-error').removeClass('has-error');
    $(this).find('span.help-block').text('');
});

// Initializes the "Edit project" dialog
$('#editProjectModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('projectId');
    var name = $('#project' + id + '-name').text();

    $('#editProject').data('saved', false);
    $('#editProject-id').val(id);
    $('#deleteProject-id').val(id);
    $('#editProject-name').val(name);

    $(this).find('div.has-error').removeClass('has-error');
    $(this).find('span.help-block').text('');
});

$('.modal').on('submit', 'form[data-async]', function (event) {
    var $form = $(this);
    var $target = $($form.attr('data-target'));

    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize(),

        success: function (data, status) {
            $target.html(data);
            $form = $target.find('form[data-async]');
            $form.find('input[data-datepicker]').datepicker();
            $('.btn-submit-on-click').on('click', function () {
                $($(this).data('form')).submit();
            });
            if ($form.data('saved')) {
                location.reload(true);
            }
        }
    });

    event.preventDefault();
});
