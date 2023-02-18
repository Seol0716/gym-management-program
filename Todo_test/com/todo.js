import React, {useState} from 'react';
import Item from './item';

import {FlatList, StyleSheet} from 'react-native';
function Todo({todos, onToggle, onDelete, onModify}) {

  return (
    <FlatList
      style={styles.list}
      data={todos}
      renderItem={({item}) => (
        <Item
          id={item.id}
          text={item.text}
          done={item.done}
          editable={item.Editable}
          onToggle={onToggle}
          onDelete={onDelete}
          onModify={onModify}
          item={item}
        />
      )}
      keyExtractor={item => item.id.toString()}
    />
  );
};

const styles = StyleSheet.create({
  view: {
    width: 380,
    height: 500,
    marginStart: 15,
    marginTop: 15,
    borderRadius: 15,
    borderWidth: 1,
    borderColor: '#800000',
    alignItems: 'center',
  },

  list: {
    flex: 1,
  }
});
export default Todo;
