import React, { forwardRef, useImperativeHandle } from 'react';
import { useForm } from 'react-hook-form';
import { createWorker } from '../../pages/workers';

const CreateWorkerForm = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const onSubmit = data => createWorker(data);
    console.log(errors);

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
          <input type="text" placeholder="Name" {...register("name", {required: true})} />
          <input type="search" 
            placeholder="Add qualifications" {...register("Qualifications",
            {required: true})} />
        <input type="text" 
            placeholder="Add Salary" {...register("salary",
            {required: true})} />
          <input type="submit" value="Create" />
        </form>
      );
    };
    
    export default CreateWorkerForm;