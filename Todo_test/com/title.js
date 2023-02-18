import React from 'react';
import {View, Text, StyleSheet} from 'react-native';

function Title() {
  return (
    <View style={styles.title}>
      <Text style={styles.text}>Todo List</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  title: {
    width: 400,
    height: 200,
    justifyContent: 'center',
    alignItems: 'center',
  },

  text: {
    fontSize: 50,
    justifyContent: 'center',
    alignItems: 'center',
    color: '#cd853f',
    marginTop: 18,
    marginLeft: 20,
  },
});

export default Title;

