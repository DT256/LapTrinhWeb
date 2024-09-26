<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- BEGIN CONTENT -->
<div class="col-md-9 col-sm-7">
  <h1>Standart forms</h1>
  <div class="content-form-page">
    <form role="form" class="form-horizontal form-without-legend" method="post" action="/WebProgramming/profile" enctype="multipart/form-data">
      <div class="form-group">
        <label class="col-lg-2 control-label" for="first-name">First Name <span class="require">*</span></label>
        <div class="col-lg-8">
          <input type="text" id="first-name" class="form-control" value="${sessionScope.account.fullname}">
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label" for="last-name">Last Name <span class="require">*</span></label>
        <div class="col-lg-8">
          <input type="text" id="last-name" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label" for="email">E-Mail <span class="require">*</span></label>
        <div class="col-lg-8">
          <input type="text" id="email" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label" for="telephone">Telephone <span class="require">*</span></label>
        <div class="col-lg-8">
          <input type="text" id="telephone" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label" for="fax">Fax</label>
        <div class="col-lg-8">
          <input type="text" id="fax" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label" for="telephone">Role <span class="require">*</span></label>
        <div class="col-lg-8">
          <input type="text" id="role" class="form-control">
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label">Checkboxes</label>
        <div class="col-lg-8 checkbox-list">
          <label>
            <input type="checkbox"> Checkbox 1
          </label>
          <label>
            <input type="checkbox"> Checkbox 2
          </label>
          <label>
            <input type="checkbox" disabled> Disabled
          </label>
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label">File input</label>
        <div class="col-lg-8">
          <input type="file" name="image">
          <p class="help-block">some help text here.</p>
        </div>
      </div>
      <div class="form-group">
        <label class="col-lg-2 control-label" for="message">Message</label>
        <div class="col-lg-8">
          <textarea class="form-control" rows="6"></textarea>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-8 col-md-offset-2 padding-left-0 padding-top-20">
          <button class="btn btn-primary" type="submit">Submit</button>
        </div>
      </div>
    </form>
  </div>
</div>
<!-- END CONTENT -->
