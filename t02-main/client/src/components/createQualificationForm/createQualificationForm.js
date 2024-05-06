import React, { forwardRef, useImperativeHandle } from 'react';
import { useForm } from 'react-hook-form';
import { parseQualification } from '../../pages/qualifications';

const CreateQualificationForm = () => {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const onSubmit = data => parseQualification(data);
    console.log(errors);

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
          <input type="text" id ="description" placeholder="description" {...register("description", {required: true})} />
    
          <input type="submit" value="Create" />
        </form>
      );
    };
    
    export default CreateQualificationForm;