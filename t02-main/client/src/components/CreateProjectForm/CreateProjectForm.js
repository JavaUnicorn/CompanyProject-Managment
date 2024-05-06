import React, { forwardRef, useImperativeHandle } from 'react';
import { useForm } from 'react-hook-form';
import { createProject } from '../../pages/projects';

const CreateProjectForm = () => {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const onSubmit = data => createProject(data);
  console.log(errors);

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input type="text" placeholder="Name" {...register("Name", {required: true})} />
      <input type="search" 
            placeholder="Add qualifications" {...register("Qualifications",
            {required: true})} />

      <input type="search" 
            placeholder="Add Workers" {...register("Workers", {})} />

      <select {...register("Status", { required: true })}>
        <option value="PLANNED">PLANNED</option>
        <option value="ACTIVE">ACTIVE</option>
        <option value="FINISHED">SUSPENDED</option>
        <option value="SUSPENDED">FINISHED</option>
      </select>

      <select {...register("Size", { required: true })}>
        <option value="BIG">BIG</option>
        <option value="MEDIUM">MEDIUM</option>
        <option value="SMALL">SMALL</option>
      </select>

      <input type="submit" value="Create" />
    </form>
  );
};

export default CreateProjectForm;