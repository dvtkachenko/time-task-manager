create or replace function DELETE_USER(p_user_name in varchar2) return boolean is
  Result boolean;
begin
  if (check_user(p_user_name) <> false) then
    delete from users u 
    where u.user_name = p_user_name;
    Result := true;
  else
    Result := false;
  end if;     
  return(Result);
end DELETE_USER;
/
